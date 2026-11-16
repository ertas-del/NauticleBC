package ca.nauticlebc.app.ui

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.seatime.LogEntry
import ca.nauticlebc.app.seatime.LogKind
import ca.nauticlebc.app.seatime.MissingDetail
import ca.nauticlebc.app.seatime.PeriodProblem
import ca.nauticlebc.app.seatime.SeaPeriod
import ca.nauticlebc.app.seatime.SeaTime
import ca.nauticlebc.app.seatime.SeaTimeBook
import ca.nauticlebc.app.seatime.SeaTimeCodec
import ca.nauticlebc.app.seatime.SharedPreferencesSeaTimeStore
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

/** Lessons that explain the records this screen keeps. */
private const val LESSON_RECORDS = "canadian-regulations.log-books-and-sea-time-records"
private const val LESSON_TRAFFIC = "radio.vts-channels-and-check-ins"

/** Victoria Traffic sector channels, as taught in the traffic check-in lesson. */
private val trafficChannels = listOf(
    "11" to R.string.seatime_channel_11,
    "74" to R.string.seatime_channel_74,
    "12" to R.string.seatime_channel_12,
    "71" to R.string.seatime_channel_71,
)

@StringRes
private fun kindLabel(kind: LogKind): Int = when (kind) {
    LogKind.DEPARTURE -> R.string.seatime_kind_departure
    LogKind.ARRIVAL -> R.string.seatime_kind_arrival
    LogKind.TRAFFIC -> R.string.seatime_kind_traffic
    LogKind.POSITION -> R.string.seatime_kind_position
    LogKind.WEATHER -> R.string.seatime_kind_weather
    LogKind.DRILL -> R.string.seatime_kind_drill
    LogKind.STEERING -> R.string.seatime_kind_steering
    LogKind.INCIDENT -> R.string.seatime_kind_incident
    LogKind.NOTE -> R.string.seatime_kind_note
}

@StringRes
private fun problemLabel(problem: PeriodProblem): Int = when (problem) {
    PeriodProblem.NO_VESSEL -> R.string.seatime_problem_vessel
    PeriodProblem.BAD_SIGN_ON -> R.string.seatime_problem_sign_on
    PeriodProblem.BAD_SIGN_OFF -> R.string.seatime_problem_sign_off
    PeriodProblem.OFF_BEFORE_ON -> R.string.seatime_problem_order
    PeriodProblem.MORE_DAYS_THAN_CALENDAR -> R.string.seatime_problem_days
    PeriodProblem.UNDERWAY_OVER_WORKED -> R.string.seatime_problem_underway
    PeriodProblem.BAD_HOURS -> R.string.seatime_problem_hours
}

@StringRes
private fun missingLabel(detail: MissingDetail): Int = when (detail) {
    MissingDetail.OFFICIAL_NUMBER -> R.string.seatime_field_official_number
    MissingDetail.PORT_OF_REGISTRY -> R.string.seatime_field_port
    MissingDetail.VESSEL_TYPE -> R.string.seatime_field_type
    MissingDetail.GROSS_TONNAGE -> R.string.seatime_field_gt
    MissingDetail.VOYAGE_CLASS -> R.string.seatime_field_voyage
    MissingDetail.RANK -> R.string.seatime_field_rank
    MissingDetail.SIGN_OFF -> R.string.seatime_field_sign_off
    MissingDetail.MASTER -> R.string.seatime_field_master
}

/** A sea period while it is being typed: numbers stay as text until Save. Kept across rotation as JSON. */
private data class PeriodForm(val base: SeaPeriod, val days: String, val hours: String, val underway: String, val drills: String, val wheel: String) {
    fun toPeriod(): SeaPeriod? {
        val d = days.trim().ifEmpty { "0" }.toIntOrNull() ?: return null
        val h = SeaTime.parseNumber(hours) ?: return null
        val u = underway.trim().ifEmpty { "0" }.toIntOrNull() ?: return null
        val dr = drills.trim().ifEmpty { "0" }.toIntOrNull() ?: return null
        val w = if (wheel.isBlank()) 0.0 else SeaTime.parseNumber(wheel) ?: return null
        if (d < 0 || u < 0 || dr < 0 || w < 0.0) return null
        return base.copy(
            vessel = base.vessel.trim(), signOn = base.signOn.trim(), signOff = base.signOff.trim(),
            daysWorked = d, hoursPerDay = h, daysUnderway = u, drills = dr, wheelHours = w,
        )
    }

    companion object {
        fun of(p: SeaPeriod, isNew: Boolean) = PeriodForm(
            base = p,
            days = if (isNew) "" else p.daysWorked.toString(),
            hours = SeaTime.format(p.hoursPerDay),
            underway = if (isNew) "" else p.daysUnderway.toString(),
            drills = if (isNew) "" else p.drills.toString(),
            wheel = if (isNew || p.wheelHours == 0.0) "" else SeaTime.format(p.wheelHours),
        )

        private const val SEP = "\u001F"
        val saver = Saver<PeriodForm?, String>(
            save = { f ->
                if (f == null) "" else listOf(f.days, f.hours, f.underway, f.drills, f.wheel, SeaTimeCodec.encodePeriod(f.base)).joinToString(SEP)
            },
            restore = { text ->
                val parts = text.split(SEP, limit = 6)
                val base = parts.getOrNull(5)?.let { SeaTimeCodec.decodePeriod(it) }
                if (base == null) null else PeriodForm(base, parts[0], parts[1], parts[2], parts[3], parts[4])
            },
        )
    }
}

private val entrySaver = Saver<LogEntry?, String>(
    save = { e -> if (e == null) "" else SeaTimeCodec.encodeEntry(e) },
    restore = { text -> if (text.isEmpty()) null else SeaTimeCodec.decodeEntry(text) },
)

/**
 * The learner's own sea time record and log book. Everything stays on the phone.
 * It is a notebook for filling in the real papers, never a substitute for them.
 */
@Composable
fun SeaTimeScreen(onOpenLesson: (String) -> Unit, onBack: () -> Unit) {
    val context = LocalContext.current
    val book = remember { SeaTimeBook(SharedPreferencesSeaTimeStore(context)) }
    var periods by remember { mutableStateOf(book.periods) }
    var log by remember { mutableStateOf(book.log) }
    var target by rememberSaveable { mutableStateOf(if (book.targetMonths > 0.0) SeaTime.format(book.targetMonths) else "") }
    var tab by rememberSaveable { mutableStateOf(0) }
    var periodForm by rememberSaveable(stateSaver = PeriodForm.saver) { mutableStateOf<PeriodForm?>(null) }
    var entryForm by rememberSaveable(stateSaver = entrySaver) { mutableStateOf<LogEntry?>(null) }
    var deletePeriodId by rememberSaveable { mutableStateOf<String?>(null) }
    var deleteEntryId by rememberSaveable { mutableStateOf<String?>(null) }

    val editing = periodForm != null || entryForm != null
    BackHandler(enabled = editing) { periodForm = null; entryForm = null }

    val title = when {
        periodForm != null -> stringResource(R.string.seatime_edit_period_title)
        entryForm != null -> stringResource(R.string.seatime_edit_entry_title)
        else -> stringResource(R.string.seatime_title)
    }
    NauticleScaffold(title = title, onBack = { if (editing) { periodForm = null; entryForm = null } else onBack() }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            val form = periodForm
            val entry = entryForm
            when {
                form != null -> PeriodEditor(
                    form = form,
                    onChange = { periodForm = it },
                    onSave = { periods = book.save(it); periodForm = null },
                    onCancel = { periodForm = null },
                )
                entry != null -> EntryEditor(
                    entry = entry,
                    onChange = { entryForm = it },
                    onSave = { log = book.save(it); entryForm = null },
                    onCancel = { entryForm = null },
                )
                else -> {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip(selected = tab == 0, onClick = { tab = 0 }, label = { Text(stringResource(R.string.seatime_tab_service)) })
                        FilterChip(selected = tab == 1, onClick = { tab = 1 }, label = { Text(stringResource(R.string.seatime_tab_log)) })
                    }
                    if (tab == 0) {
                        ServiceTab(
                            periods = periods,
                            target = target,
                            onTarget = { target = it; book.setTarget(SeaTime.parseNumber(it) ?: 0.0) },
                            onAdd = {
                                // Start from the last vessel so a new spell on the same boat is a few taps.
                                val last = periods.firstOrNull()
                                val fresh = (last ?: SeaPeriod(id = "")).copy(
                                    id = UUID.randomUUID().toString(),
                                    signOn = LocalDate.now().toString(), signOff = "",
                                    daysWorked = 0, daysUnderway = 0, drills = 0, wheelHours = 0.0, notes = "",
                                    hasDischarge = false, hasTestimonial = false,
                                )
                                periodForm = PeriodForm.of(fresh, isNew = true)
                            },
                            onEdit = { periodForm = PeriodForm.of(it, isNew = false) },
                            onDelete = { deletePeriodId = it.id },
                            onShare = { share(context, serviceText(context, periods)) },
                            onOpenLesson = { onOpenLesson(LESSON_RECORDS) },
                        )
                    } else {
                        LogTab(
                            log = log,
                            onAdd = { kind ->
                                entryForm = LogEntry(
                                    id = UUID.randomUUID().toString(),
                                    date = LocalDate.now().toString(),
                                    time = LocalTime.now().let { "%02d%02d".format(it.hour, it.minute) },
                                    kind = kind.name,
                                    vessel = log.firstOrNull()?.vessel ?: periods.firstOrNull()?.vessel.orEmpty(),
                                    channel = if (kind == LogKind.TRAFFIC) log.firstOrNull { it.logKind == LogKind.TRAFFIC }?.channel.orEmpty() else "",
                                )
                            },
                            onEdit = { entryForm = it },
                            onDelete = { deleteEntryId = it.id },
                            onShare = { share(context, logText(context, log)) },
                            onOpenLesson = { onOpenLesson(LESSON_TRAFFIC) },
                        )
                    }
                    Text(stringResource(R.string.seatime_private_note), style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }

    deletePeriodId?.let { id ->
        ConfirmDelete(
            onConfirm = { periods = book.deletePeriod(id); deletePeriodId = null },
            onDismiss = { deletePeriodId = null },
        )
    }
    deleteEntryId?.let { id ->
        ConfirmDelete(
            onConfirm = { log = book.deleteEntry(id); deleteEntryId = null },
            onDismiss = { deleteEntryId = null },
        )
    }
}

@Composable
private fun ConfirmDelete(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.seatime_delete_title)) },
        text = { Text(stringResource(R.string.seatime_delete_text)) },
        confirmButton = { TextButton(onClick = onConfirm) { Text(stringResource(R.string.seatime_delete)) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text(stringResource(R.string.seatime_cancel)) } },
    )
}

@Composable
private fun ServiceTab(
    periods: List<SeaPeriod>,
    target: String,
    onTarget: (String) -> Unit,
    onAdd: () -> Unit,
    onEdit: (SeaPeriod) -> Unit,
    onDelete: (SeaPeriod) -> Unit,
    onShare: () -> Unit,
    onOpenLesson: () -> Unit,
) {
    val summary = remember(periods) { SeaTime.summarize(periods) }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = stringResource(R.string.seatime_months, SeaTime.format(summary.months)),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.semantics { heading() },
            )
            Text(
                stringResource(R.string.seatime_total_line, SeaTime.format(summary.creditedDays), summary.daysWorked),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(stringResource(R.string.seatime_rule), style = MaterialTheme.typography.bodyMedium)
            if (summary.wheelHours > 0.0 || summary.drills > 0) {
                Text(
                    stringResource(R.string.seatime_wheel_drills, SeaTime.format(summary.wheelHours), summary.drills),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            OutlinedTextField(
                value = target,
                onValueChange = onTarget,
                singleLine = true,
                label = { Text(stringResource(R.string.seatime_target_label)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
            )
            val targetMonths = SeaTime.parseNumber(target)
            if (targetMonths != null && targetMonths > 0.0) {
                val left = SeaTime.remainingDays(targetMonths, summary.creditedDays)
                Text(
                    text = if (left <= 0.0) stringResource(R.string.seatime_target_met)
                    else stringResource(
                        R.string.seatime_target_left,
                        SeaTime.format(left),
                        Math.ceil(left / 1.5).toInt(),
                        Math.ceil(left).toInt(),
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
                )
            }
            if (periods.isNotEmpty() && (summary.needTestimonial > 0 || summary.needDischarge > 0)) {
                Text(
                    stringResource(R.string.seatime_papers_to_chase, summary.needTestimonial, summary.needDischarge),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
    Button(onClick = onAdd, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_add_period)) }
    if (periods.isEmpty()) {
        Text(stringResource(R.string.seatime_empty_periods), style = MaterialTheme.typography.bodyLarge)
    }
    periods.forEach { p -> PeriodCard(p, onEdit = { onEdit(p) }, onDelete = { onDelete(p) }) }
    if (periods.isNotEmpty()) {
        OutlinedButton(onClick = onShare, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_share_service)) }
    }
    OutlinedButton(onClick = onOpenLesson, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_lesson_records)) }
}

@Composable
private fun PeriodCard(p: SeaPeriod, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                p.vessel.ifBlank { stringResource(R.string.seatime_unnamed_vessel) },
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() },
            )
            val until = p.signOff.ifBlank { stringResource(R.string.seatime_still_aboard) }
            Text(
                if (p.rank.isBlank()) stringResource(R.string.seatime_dates, p.signOn, until)
                else stringResource(R.string.seatime_rank_dates, p.rank, p.signOn, until),
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                stringResource(R.string.seatime_period_sum, p.daysWorked, SeaTime.format(p.hoursPerDay), SeaTime.format(p.creditedDays)),
                style = MaterialTheme.typography.bodyLarge,
            )
            if (p.hoursPerDay > SeaTime.MAX_HOURS_PER_DAY) {
                Text(stringResource(R.string.seatime_capped), style = MaterialTheme.typography.bodyMedium)
            }
            Text(
                stringResource(if (p.hasTestimonial) R.string.seatime_testimonial_yes else R.string.seatime_testimonial_no),
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                stringResource(if (p.hasDischarge) R.string.seatime_discharge_yes else R.string.seatime_discharge_no),
                style = MaterialTheme.typography.bodyMedium,
            )
            val missing = SeaTime.missing(p).filter { it != MissingDetail.SIGN_OFF }
            if (missing.isNotEmpty()) {
                val names = missing.map { stringResource(missingLabel(it)) }.joinToString(", ")
                Text(stringResource(R.string.seatime_still_blank, names), style = MaterialTheme.typography.bodyMedium)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = onEdit) { Text(stringResource(R.string.seatime_edit)) }
                TextButton(onClick = onDelete) { Text(stringResource(R.string.seatime_delete)) }
            }
        }
    }
}

@Composable
private fun Field(
    @StringRes label: Int,
    value: String,
    onChange: (String) -> Unit,
    keyboard: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        singleLine = singleLine,
        label = { Text(stringResource(label)) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboard),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun SectionHeading(@StringRes text: Int) {
    Text(stringResource(text), style = MaterialTheme.typography.titleMedium, modifier = Modifier.semantics { heading() })
}

@Composable
private fun CheckRow(@StringRes label: Int, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(value = checked, role = Role.Checkbox, onValueChange = onChange)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Checkbox(checked = checked, onCheckedChange = null)
        Text(stringResource(label), style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun PeriodEditor(form: PeriodForm, onChange: (PeriodForm) -> Unit, onSave: (SeaPeriod) -> Unit, onCancel: () -> Unit) {
    val b = form.base
    fun base(next: SeaPeriod) = onChange(form.copy(base = next))
    var tried by rememberSaveable { mutableStateOf(false) }

    SectionHeading(R.string.seatime_section_vessel)
    Text(stringResource(R.string.seatime_vessel_hint), style = MaterialTheme.typography.bodyMedium)
    Field(R.string.seatime_field_vessel, b.vessel, { base(b.copy(vessel = it)) })
    Field(R.string.seatime_field_official_number, b.officialNumber, { base(b.copy(officialNumber = it)) })
    Field(R.string.seatime_field_port, b.portOfRegistry, { base(b.copy(portOfRegistry = it)) })
    Field(R.string.seatime_field_type, b.vesselType, { base(b.copy(vesselType = it)) })
    Field(R.string.seatime_field_gt, b.grossTonnage, { base(b.copy(grossTonnage = it)) }, KeyboardType.Decimal)
    Field(R.string.seatime_field_voyage, b.voyageClass, { base(b.copy(voyageClass = it)) })

    SectionHeading(R.string.seatime_section_service)
    Field(R.string.seatime_field_rank, b.rank, { base(b.copy(rank = it)) })
    Field(R.string.seatime_field_sign_on, b.signOn, { base(b.copy(signOn = it)) })
    Field(R.string.seatime_field_sign_off, b.signOff, { base(b.copy(signOff = it)) })
    OutlinedButton(onClick = { base(b.copy(signOff = LocalDate.now().toString())) }) {
        Text(stringResource(R.string.seatime_sign_off_today))
    }
    Field(R.string.seatime_field_days, form.days, { onChange(form.copy(days = it)) }, KeyboardType.Number)
    SeaTime.calendarDays(b.signOn, b.signOff)?.let { span ->
        Text(stringResource(R.string.seatime_calendar_days, span), style = MaterialTheme.typography.bodyMedium)
    }
    Field(R.string.seatime_field_hours, form.hours, { onChange(form.copy(hours = it)) }, KeyboardType.Decimal)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf("8", "10", "12").forEach { h ->
            FilterChip(selected = form.hours == h, onClick = { onChange(form.copy(hours = h)) }, label = { Text(stringResource(R.string.seatime_hours_chip, h)) })
        }
    }
    Field(R.string.seatime_field_underway, form.underway, { onChange(form.copy(underway = it)) }, KeyboardType.Number)
    Field(R.string.seatime_field_drills, form.drills, { onChange(form.copy(drills = it)) }, KeyboardType.Number)
    Field(R.string.seatime_field_wheel, form.wheel, { onChange(form.copy(wheel = it)) }, KeyboardType.Decimal)
    Field(R.string.seatime_field_master, b.master, { base(b.copy(master = it)) })
    Field(R.string.seatime_field_notes, b.notes, { base(b.copy(notes = it)) }, singleLine = false)

    SectionHeading(R.string.seatime_section_papers)
    CheckRow(R.string.seatime_check_discharge, b.hasDischarge) { base(b.copy(hasDischarge = it)) }
    CheckRow(R.string.seatime_check_testimonial, b.hasTestimonial) { base(b.copy(hasTestimonial = it)) }

    val period = form.toPeriod()
    val problems = period?.let { SeaTime.problems(it) }
    if (period != null) {
        Text(
            stringResource(R.string.seatime_period_sum, period.daysWorked, SeaTime.format(period.hoursPerDay), SeaTime.format(period.creditedDays)),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
        )
    }
    if (tried) {
        Column(modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite }) {
            if (period == null) {
                Text(stringResource(R.string.seatime_problem_numbers), color = MaterialTheme.colorScheme.error)
            }
            problems.orEmpty().forEach { Text(stringResource(problemLabel(it)), color = MaterialTheme.colorScheme.error) }
        }
    }
    Button(
        onClick = {
            tried = true
            if (period != null && problems.isNullOrEmpty()) onSave(period)
        },
        modifier = Modifier.fillMaxWidth(),
    ) { Text(stringResource(R.string.seatime_save)) }
    OutlinedButton(onClick = onCancel, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_cancel)) }
}

@Composable
private fun LogTab(
    log: List<LogEntry>,
    onAdd: (LogKind) -> Unit,
    onEdit: (LogEntry) -> Unit,
    onDelete: (LogEntry) -> Unit,
    onShare: () -> Unit,
    onOpenLesson: () -> Unit,
) {
    Button(onClick = { onAdd(LogKind.TRAFFIC) }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_add_traffic)) }
    OutlinedButton(onClick = { onAdd(LogKind.NOTE) }, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_add_entry)) }
    if (log.isEmpty()) {
        Text(stringResource(R.string.seatime_empty_log), style = MaterialTheme.typography.bodyLarge)
    }
    log.forEach { e ->
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    stringResource(R.string.seatime_log_heading, e.date, e.time, stringResource(kindLabel(e.logKind))),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.semantics { heading() },
                )
                if (e.vessel.isNotBlank()) Text(e.vessel, style = MaterialTheme.typography.bodyMedium)
                if (e.place.isNotBlank()) Text(e.place, style = MaterialTheme.typography.bodyLarge)
                if (e.channel.isNotBlank()) Text(stringResource(R.string.seatime_log_channel, e.channel), style = MaterialTheme.typography.bodyLarge)
                if (e.text.isNotBlank()) Text(e.text, style = MaterialTheme.typography.bodyLarge)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(onClick = { onEdit(e) }) { Text(stringResource(R.string.seatime_edit)) }
                    TextButton(onClick = { onDelete(e) }) { Text(stringResource(R.string.seatime_delete)) }
                }
            }
        }
    }
    if (log.isNotEmpty()) {
        OutlinedButton(onClick = onShare, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_share_log)) }
    }
    OutlinedButton(onClick = onOpenLesson, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_lesson_traffic)) }
}

@Composable
private fun EntryEditor(entry: LogEntry, onChange: (LogEntry) -> Unit, onSave: (LogEntry) -> Unit, onCancel: () -> Unit) {
    var tried by rememberSaveable { mutableStateOf(false) }
    val kind = entry.logKind

    Row(modifier = Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        LogKind.entries.forEach { k ->
            FilterChip(selected = kind == k, onClick = { onChange(entry.copy(kind = k.name)) }, label = { Text(stringResource(kindLabel(k))) })
        }
    }
    Field(R.string.seatime_field_date, entry.date, { onChange(entry.copy(date = it)) })
    Field(R.string.seatime_field_time, entry.time, { onChange(entry.copy(time = it.filter { c -> c.isDigit() }.take(4))) }, KeyboardType.Number)
    Field(R.string.seatime_field_vessel, entry.vessel, { onChange(entry.copy(vessel = it)) })
    Field(
        if (kind == LogKind.TRAFFIC) R.string.seatime_field_calling_in_point else R.string.seatime_field_place,
        entry.place,
        { onChange(entry.copy(place = it)) },
    )
    if (kind == LogKind.TRAFFIC) {
        SectionHeading(R.string.seatime_channel_heading)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            trafficChannels.forEach { (channel, label) ->
                FilterChip(
                    selected = entry.channel == channel,
                    onClick = { onChange(entry.copy(channel = channel)) },
                    label = { Text(stringResource(label)) },
                )
            }
        }
        Field(R.string.seatime_field_channel, entry.channel, { onChange(entry.copy(channel = it.take(4))) })
        Text(stringResource(R.string.seatime_channel_note), style = MaterialTheme.typography.bodySmall)
        Text(stringResource(R.string.seatime_traffic_prompt), style = MaterialTheme.typography.bodyMedium)
    }
    Field(
        if (kind == LogKind.TRAFFIC) R.string.seatime_field_traffic_text else R.string.seatime_field_text,
        entry.text,
        { onChange(entry.copy(text = it)) },
        singleLine = false,
    )

    val dateOk = SeaTime.parseDate(entry.date) != null
    val timeOk = SeaTime.isValidTime(entry.time)
    if (tried && (!dateOk || !timeOk)) {
        Column(modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite }) {
            if (!dateOk) Text(stringResource(R.string.seatime_problem_date), color = MaterialTheme.colorScheme.error)
            if (!timeOk) Text(stringResource(R.string.seatime_problem_time), color = MaterialTheme.colorScheme.error)
        }
    }
    Button(
        onClick = {
            tried = true
            if (dateOk && timeOk) onSave(entry.copy(date = entry.date.trim(), channel = if (kind == LogKind.TRAFFIC) entry.channel.trim() else ""))
        },
        modifier = Modifier.fillMaxWidth(),
    ) { Text(stringResource(R.string.seatime_save)) }
    OutlinedButton(onClick = onCancel, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.seatime_cancel)) }
}

private fun share(context: Context, text: String) {
    val send = Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, text)
    context.startActivity(Intent.createChooser(send, null))
}

/** Plain text of the sea time record, in testimonial order, for copying onto the real forms. */
private fun serviceText(context: Context, periods: List<SeaPeriod>): String {
    val s = SeaTime.summarize(periods)
    val yes = context.getString(R.string.seatime_yes)
    val no = context.getString(R.string.seatime_no)
    return buildString {
        appendLine(context.getString(R.string.seatime_export_service_title))
        appendLine(context.getString(R.string.seatime_export_not_official))
        appendLine(context.getString(R.string.seatime_export_total, SeaTime.format(s.creditedDays), SeaTime.format(s.months), s.daysWorked))
        periods.forEach { p ->
            appendLine()
            appendLine(context.getString(R.string.seatime_export_vessel, p.vessel, p.officialNumber, p.portOfRegistry, p.vesselType, p.grossTonnage, p.voyageClass))
            appendLine(context.getString(R.string.seatime_export_service, p.rank, p.signOn, p.signOff.ifBlank { context.getString(R.string.seatime_still_aboard) }))
            appendLine(context.getString(R.string.seatime_export_days, p.daysWorked, SeaTime.format(p.hoursPerDay), SeaTime.format(p.creditedDays), p.daysUnderway))
            appendLine(context.getString(R.string.seatime_export_extras, p.drills, SeaTime.format(p.wheelHours), p.master))
            appendLine(context.getString(R.string.seatime_export_papers, if (p.hasDischarge) yes else no, if (p.hasTestimonial) yes else no))
            if (p.notes.isNotBlank()) appendLine(p.notes)
        }
    }.trimEnd()
}

private fun logText(context: Context, log: List<LogEntry>): String = buildString {
    appendLine(context.getString(R.string.seatime_export_log_title))
    appendLine(context.getString(R.string.seatime_export_not_official))
    // Oldest first, the way a log book reads.
    log.asReversed().forEach { e ->
        val parts = listOfNotNull(
            e.date, e.time, context.getString(kindLabel(e.logKind)),
            e.vessel.ifBlank { null }, e.place.ifBlank { null },
            e.channel.ifBlank { null }?.let { context.getString(R.string.seatime_log_channel, it) },
            e.text.ifBlank { null },
        )
        appendLine(parts.joinToString("  "))
    }
}.trimEnd()
