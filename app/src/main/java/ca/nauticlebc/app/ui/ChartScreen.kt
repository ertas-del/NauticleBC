package ca.nauticlebc.app.ui

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.Chart
import ca.nauticlebc.app.content.ChartExercise
import ca.nauticlebc.app.content.ChartGeometry
import ca.nauticlebc.app.content.ChartPlot
import ca.nauticlebc.app.content.ChartPos
import ca.nauticlebc.app.content.ContentBundle
import ca.nauticlebc.app.content.LineKind
import ca.nauticlebc.app.content.Plot
import ca.nauticlebc.app.content.PlotCheck
import ca.nauticlebc.app.content.PlotNeed
import ca.nauticlebc.app.content.PlotUnit
import kotlin.math.hypot
import kotlin.math.min

private val Water = Color(0xFFE6F1F6)
private val LandFill = Color(0xFFF2E7BC)
private val LandEdge = Color(0xFF6B5B2A)
private val ChartInk = Color(0xFF1F2A33)
private val Magenta = Color(0xFFA6246F)
private val GridInk = Color(0x33355A70)
private val PencilRed = Color(0xFFD32F2F)
private val CourseBlue = Color(0xFF1546A0)
private val LineGreen = Color(0xFF1B7A3A)

private enum class PlotTool { Point, Measure, LayOff }

/**
 * Practice chart: a fictional chart drawn from data. Pan and zoom, plot points, measure with the
 * parallel rule and dividers, lay off courses and bearings with the plotter, then grade the exercise.
 */
@Composable
fun ChartScreen(bundle: ContentBundle, onBack: () -> Unit) {
    val chart = bundle.charts.firstOrNull()
    if (chart == null) {
        MessageScreen(
            title = stringResource(R.string.chart_screen_title),
            message = stringResource(R.string.chart_none),
            onBack = onBack,
        )
        return
    }
    val geo = remember(chart) { ChartGeometry(chart.extent) }
    val exercises = remember(chart) { bundle.chartExercises.filter { it.chartId == chart.id } }
    val variationEast = remember(chart) { ChartPlot.variation(chart.variation) }
    val snapTargets = remember(chart) { chart.aids.map { it.position } + chart.hazards.map { it.position } }

    var exerciseIndex by rememberSaveable { mutableStateOf(0) }
    var showMethod by rememberSaveable(exerciseIndex) { mutableStateOf(false) }
    var showAnswer by rememberSaveable(exerciseIndex) { mutableStateOf(false) }
    var panelOpen by rememberSaveable { mutableStateOf(true) }
    var zoom by remember { mutableFloatStateOf(1f) }
    var pan by remember { mutableStateOf(Offset.Zero) }
    var cursor by remember { mutableStateOf<ChartPos?>(null) }
    var tool by remember { mutableStateOf(PlotTool.Point) }
    var plot by remember(exerciseIndex) { mutableStateOf(Plot()) }
    var history by remember(exerciseIndex) { mutableStateOf(listOf<Plot>()) }
    var typed by remember(exerciseIndex) { mutableStateOf(mapOf<String, String>()) }
    var grade by remember(exerciseIndex) { mutableStateOf<List<PlotCheck>>(emptyList()) }
    val exercise = exercises.getOrNull(exerciseIndex.coerceIn(0, (exercises.size - 1).coerceAtLeast(0)))

    fun apply(next: Plot) {
        if (next == plot) return
        history = (history + plot).takeLast(40)
        plot = next
        grade = emptyList()
    }

    NauticleScaffold(title = chart.title, onBack = onBack) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text(
                text = stringResource(R.string.chart_fictional_banner),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            )
            ChartCanvas(
                chart = chart,
                geo = geo,
                zoom = zoom,
                pan = pan,
                cursor = cursor,
                plot = plot,
                rose = chart.compassRoses.firstOrNull()?.position,
                variationEast = variationEast,
                snapTargets = if (tool == PlotTool.Point) emptyList() else snapTargets,
                onTransform = { z, p -> zoom = z; pan = p },
                onTap = { p ->
                    cursor = p
                    when (tool) {
                        PlotTool.Point -> apply(plot.point(p))
                        PlotTool.Measure -> apply(plot.measureTap(p))
                        PlotTool.LayOff -> apply(plot.withStart(p))
                    }
                },
                modifier = Modifier.weight(1f).fillMaxWidth(),
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 2.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ToolReadout(geo, plot, tool, cursor, variationEast, Modifier.weight(1f))
                TextButton(onClick = { zoom = 1f; pan = Offset.Zero }) {
                    Text(stringResource(R.string.chart_reset))
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().heightIn(max = if (panelOpen) 360.dp else 120.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    FilterChip(tool == PlotTool.Point, { tool = PlotTool.Point }, { Text(stringResource(R.string.chart_tool_point)) })
                    FilterChip(tool == PlotTool.Measure, { tool = PlotTool.Measure }, { Text(stringResource(R.string.chart_tool_measure)) })
                    FilterChip(tool == PlotTool.LayOff, { tool = PlotTool.LayOff }, { Text(stringResource(R.string.chart_tool_lay_off)) })
                    TextButton(
                        onClick = { history.lastOrNull()?.let { plot = it; history = history.dropLast(1); grade = emptyList() } },
                        enabled = history.isNotEmpty(),
                    ) { Text(stringResource(R.string.chart_undo)) }
                    TextButton(onClick = { apply(Plot()); cursor = null }) { Text(stringResource(R.string.chart_clear_plot)) }
                    TextButton(onClick = { panelOpen = !panelOpen }) {
                        Text(stringResource(if (panelOpen) R.string.chart_panel_hide else R.string.chart_panel_show))
                    }
                }
                when (tool) {
                    PlotTool.Point -> PointPanel(chart, onPlot = { apply(plot.point(it)); cursor = it })
                    PlotTool.Measure -> Text(stringResource(R.string.chart_measure_help), style = MaterialTheme.typography.bodyMedium)
                    PlotTool.LayOff -> LayOffPanel(
                        start = plot.start,
                        onDraw = { bearing, distance -> apply(ChartPlot.layOff(geo, plot, bearing, distance)) },
                    )
                }
                if (exercise == null) {
                    Text(stringResource(R.string.chart_no_exercises), style = MaterialTheme.typography.bodyMedium)
                } else {
                    ExercisePanel(
                        chart = chart,
                        exercises = exercises,
                        exercise = exercise,
                        index = exerciseIndex,
                        onIndex = { exerciseIndex = it },
                        typed = typed,
                        onTyped = { key, value -> typed = typed + (key to value); grade = emptyList() },
                        onGrade = { grade = ChartPlot.grade(geo, exercise, plot, typed, chart.extent) },
                        grade = grade,
                        showMethod = showMethod,
                        onShowMethod = { showMethod = it },
                        showAnswer = showAnswer,
                        onShowAnswer = { showAnswer = it },
                    )
                }
            }
        }
    }
}

@Composable
private fun ToolReadout(
    geo: ChartGeometry,
    plot: Plot,
    tool: PlotTool,
    cursor: ChartPos?,
    variationEast: Double,
    modifier: Modifier = Modifier,
) {
    val line = plot.lastMeasured
    val text = when {
        tool == PlotTool.Measure && plot.pending != null -> stringResource(R.string.chart_measure_second)
        tool != PlotTool.Point && line != null -> {
            val t = geo.bearingTrue(line.from, line.to)
            stringResource(
                R.string.chart_line_readout,
                threeFigures(t),
                threeFigures(ChartPlot.magnetic(t, variationEast)),
                ChartPlot.format(Math.round(geo.distanceNm(line.from, line.to) * 100) / 100.0),
            )
        }
        tool == PlotTool.LayOff -> stringResource(R.string.chart_lay_off_hint)
        tool == PlotTool.Measure -> stringResource(R.string.chart_measure_first)
        cursor != null -> stringResource(R.string.chart_cursor, ChartGeometry.formatLat(cursor.lat), ChartGeometry.formatLon(cursor.lon))
        else -> stringResource(R.string.chart_tap_hint)
    }
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.semantics { liveRegion = LiveRegionMode.Polite },
    )
}

private fun threeFigures(deg: Double): String = "%03d".format(java.util.Locale.US, Math.round(deg).toInt().mod(360))

/** Plot a point by typing its latitude and longitude, the way you would from the chart scales. */
@Composable
private fun PointPanel(chart: Chart, onPlot: (ChartPos) -> Unit) {
    var lat by rememberSaveable { mutableStateOf("") }
    var lon by rememberSaveable { mutableStateOf("") }
    var bad by remember { mutableStateOf(false) }
    Text(stringResource(R.string.chart_point_help), style = MaterialTheme.typography.bodyMedium)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = lat,
            onValueChange = { lat = it; bad = false },
            label = { Text(stringResource(R.string.chart_lat_hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
            singleLine = true,
            modifier = Modifier.weight(1f),
        )
        OutlinedTextField(
            value = lon,
            onValueChange = { lon = it; bad = false },
            label = { Text(stringResource(R.string.chart_lon_hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
            singleLine = true,
            modifier = Modifier.weight(1f),
        )
    }
    if (bad) Text(stringResource(R.string.chart_bad_position), color = MaterialTheme.colorScheme.error)
    OutlinedButton(onClick = {
        val la = ChartPlot.parseDegMin(lat, chart.extent.south < 0)
        val lo = ChartPlot.parseDegMin(lon, chart.extent.west < 0)
        val e = chart.extent
        if (la == null || lo == null || la !in e.south..e.north || lo !in e.west..e.east) bad = true
        else onPlot(ChartPos(la, lo))
    }) { Text(stringResource(R.string.chart_plot_position)) }
}

/** The plotter: a true bearing from the start, with or without a distance. */
@Composable
private fun LayOffPanel(start: ChartPos?, onDraw: (Double, Double?) -> Unit) {
    var bearing by rememberSaveable { mutableStateOf("") }
    var distance by rememberSaveable { mutableStateOf("") }
    var bad by remember { mutableStateOf(false) }
    Text(
        text = if (start == null) stringResource(R.string.chart_lay_off_need_start)
        else stringResource(R.string.chart_lay_off_start, ChartGeometry.formatLat(start.lat), ChartGeometry.formatLon(start.lon)),
        style = MaterialTheme.typography.bodyMedium,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = bearing,
            onValueChange = { bearing = it; bad = false },
            label = { Text(stringResource(R.string.chart_bearing_hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.weight(1f),
        )
        OutlinedTextField(
            value = distance,
            onValueChange = { distance = it; bad = false },
            label = { Text(stringResource(R.string.chart_distance_hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.weight(1f),
        )
    }
    Text(stringResource(R.string.chart_lay_off_help), style = MaterialTheme.typography.bodySmall)
    if (bad) Text(stringResource(R.string.chart_bad_bearing), color = MaterialTheme.colorScheme.error)
    Button(
        enabled = start != null,
        onClick = {
            val b = ChartPlot.parseNumber(bearing)
            val d = if (distance.isBlank()) null else ChartPlot.parseNumber(distance)
            if (b == null || b < 0.0 || b > 360.0 || (distance.isNotBlank() && (d == null || d <= 0.0))) bad = true
            else onDraw(b, d)
        },
    ) { Text(stringResource(R.string.chart_draw_line)) }
}

@Composable
private fun ExercisePanel(
    chart: Chart,
    exercises: List<ChartExercise>,
    exercise: ChartExercise,
    index: Int,
    onIndex: (Int) -> Unit,
    typed: Map<String, String>,
    onTyped: (String, String) -> Unit,
    onGrade: () -> Unit,
    grade: List<PlotCheck>,
    showMethod: Boolean,
    onShowMethod: (Boolean) -> Unit,
    showAnswer: Boolean,
    onShowAnswer: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        exercises.forEachIndexed { i, _ ->
            FilterChip(
                selected = i == index,
                onClick = { onIndex(i) },
                label = { Text(stringResource(R.string.chart_exercise_n, i + 1)) },
            )
        }
    }
    Text(exercise.prompt, style = MaterialTheme.typography.bodyLarge)
    exercise.answers.forEachIndexed { i, answer ->
        if (!ChartPlot.isTyped(exercise, answer)) return@forEachIndexed
        when (answer.kind) {
            "position" -> Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = typed[i.toString()].orEmpty(),
                    onValueChange = { onTyped(i.toString(), it) },
                    label = { Text(stringResource(R.string.chart_lat_hint)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                )
                OutlinedTextField(
                    value = typed["$i.lon"].orEmpty(),
                    onValueChange = { onTyped("$i.lon", it) },
                    label = { Text(stringResource(R.string.chart_lon_hint)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                )
            }
            else -> OutlinedTextField(
                value = typed[i.toString()].orEmpty(),
                onValueChange = { onTyped(i.toString(), it) },
                label = {
                    Text(
                        if (answer.kind == "time") stringResource(R.string.chart_time_answer, answer.label)
                        else answer.label,
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = if (answer.kind == "time") KeyboardType.Ascii else KeyboardType.Decimal,
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
    if (exercise.answers.any { !ChartPlot.isTyped(exercise, it) }) {
        Text(stringResource(R.string.chart_grade_plot_help), style = MaterialTheme.typography.bodySmall)
    }
    Button(onClick = onGrade, modifier = Modifier.fillMaxWidth()) { Text(stringResource(R.string.chart_grade)) }
    if (grade.isNotEmpty()) {
        Column(
            modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            grade.forEach { check ->
                val passed = (check as? PlotCheck.Result)?.passed ?: (check as? PlotCheck.PositionResult)?.passed
                Text(
                    text = gradeText(check),
                    style = MaterialTheme.typography.bodyMedium,
                    color = when (passed) {
                        true -> LineGreen
                        false -> MaterialTheme.colorScheme.error
                        null -> MaterialTheme.colorScheme.onSurfaceVariant
                    },
                )
            }
        }
    }
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(onClick = { onShowMethod(!showMethod) }) {
            Text(stringResource(if (showMethod) R.string.chart_hide_method else R.string.chart_show_method))
        }
        OutlinedButton(onClick = { onShowAnswer(!showAnswer) }) {
            Text(stringResource(if (showAnswer) R.string.chart_hide_answer else R.string.chart_show_answer))
        }
    }
    if (showMethod) {
        exercise.method.forEachIndexed { i, step ->
            Text("${i + 1}. $step", style = MaterialTheme.typography.bodyMedium)
        }
    }
    if (showAnswer) {
        exercise.answers.forEach { a ->
            val pos = a.position
            val shown = when {
                pos != null -> "${ChartGeometry.formatLat(pos.lat)} ${ChartGeometry.formatLon(pos.lon)}"
                a.kind == "time" -> ChartPlot.clock(a.number ?: 0.0)
                else -> ChartPlot.format(a.number ?: 0.0) + (a.unit?.let { " $it" } ?: "")
            }
            val tol = a.tolerance?.let { stringResource(R.string.chart_tolerance, ChartPlot.format(it)) }.orEmpty()
            Text("${a.label}: $shown $tol", style = MaterialTheme.typography.bodyMedium)
        }
    }
    Text(chart.areaNote, style = MaterialTheme.typography.bodySmall)
}

@Composable
private fun withUnit(value: String, unit: PlotUnit): String = when (unit) {
    PlotUnit.Degrees -> stringResource(R.string.chart_unit_degrees, value)
    PlotUnit.NauticalMiles -> stringResource(R.string.chart_unit_nm, value)
    PlotUnit.Metres -> stringResource(R.string.chart_unit_metres, value)
    PlotUnit.Time -> value
    PlotUnit.None -> value
}

@Composable
private fun gradeText(check: PlotCheck): String = when (check) {
    is PlotCheck.NeedPlot -> stringResource(
        if (check.what == PlotNeed.Position) R.string.chart_need_position else R.string.chart_need_line,
        check.label,
    )
    is PlotCheck.NeedTyped -> stringResource(R.string.chart_need_typed, check.label)
    is PlotCheck.PositionResult -> stringResource(
        if (check.passed) R.string.chart_grade_pos_pass else R.string.chart_grade_pos_fail,
        check.label,
        check.offNm,
        check.tolerance,
    )
    is PlotCheck.Result -> stringResource(
        if (check.passed) R.string.chart_grade_pass else R.string.chart_grade_fail,
        check.label,
        withUnit(check.measured, check.unit),
        withUnit(check.expected, check.unit),
        if (check.unit == PlotUnit.Time) stringResource(R.string.chart_unit_minutes, check.tolerance)
        else withUnit(check.tolerance, check.unit),
    )
}

@Composable
private fun ChartCanvas(
    chart: Chart,
    geo: ChartGeometry,
    zoom: Float,
    pan: Offset,
    cursor: ChartPos?,
    plot: Plot,
    rose: ChartPos?,
    variationEast: Double,
    snapTargets: List<ChartPos>,
    onTransform: (Float, Offset) -> Unit,
    onTap: (ChartPos) -> Unit,
    modifier: Modifier = Modifier,
) {
    val description = stringResource(R.string.chart_content_description, chart.title)
    val textPaint = remember {
        Paint(Paint.ANTI_ALIAS_FLAG).apply { color = ChartInk.toArgb(); typeface = Typeface.SANS_SERIF }
    }
    val namePaint = remember {
        Paint(Paint.ANTI_ALIAS_FLAG).apply { color = ChartInk.toArgb(); typeface = Typeface.create(Typeface.SERIF, Typeface.ITALIC) }
    }
    val aidPaint = remember {
        Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Magenta.toArgb(); typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD) }
    }
    val rulePaint = remember {
        Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Magenta.toArgb(); typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD) }
    }
    Canvas(
        modifier = modifier
            .clipToBounds()
            .background(Water)
            .semantics { contentDescription = description }
            .pointerInput(geo, zoom, pan) {
                detectTransformGestures { centroid, drag, scaleChange, _ ->
                    val w = size.width.toFloat()
                    val h = size.height.toFloat()
                    val newZoom = (zoom * scaleChange).coerceIn(1f, 12f)
                    val ratio = newZoom / zoom
                    val k = baseScale(geo, w, h) * zoom
                    val k2 = baseScale(geo, w, h) * newZoom
                    val oldOrigin = Offset(fitOffsetX(geo, w, k) + pan.x, fitOffsetY(geo, h, k) + pan.y)
                    // Keep the chart point under the fingers fixed while zooming, then follow the drag.
                    val newOrigin = centroid - (centroid - oldOrigin) * ratio + drag
                    val newPan = Offset(newOrigin.x - fitOffsetX(geo, w, k2), newOrigin.y - fitOffsetY(geo, h, k2))
                    onTransform(newZoom, clampPan(newPan, newZoom, w, h))
                }
            }
            .pointerInput(geo, zoom, pan, snapTargets) {
                detectTapGestures { tap ->
                    val k = baseScale(geo, size.width.toFloat(), size.height.toFloat()) * zoom
                    val ox = fitOffsetX(geo, size.width.toFloat(), k) + pan.x
                    val oy = fitOffsetY(geo, size.height.toFloat(), k) + pan.y
                    // A tap near a light, mark or hazard snaps to it, like putting the pencil on the symbol.
                    val snapPx = 28.dp.toPx()
                    val snapped = snapTargets.minByOrNull { p ->
                        hypot(ox + geo.x(p) * k - tap.x, oy + (geo.heightNm - geo.y(p)) * k - tap.y)
                    }?.takeIf { p ->
                        hypot(ox + geo.x(p) * k - tap.x, oy + (geo.heightNm - geo.y(p)) * k - tap.y) <= snapPx
                    }
                    val xNm = ((tap.x - ox) / k).toDouble()
                    val yNm = geo.heightNm - ((tap.y - oy) / k).toDouble()
                    onTap(snapped ?: geo.position(xNm, yNm))
                }
            },
    ) {
        val w = size.width
        val h = size.height
        val k = baseScale(geo, w, h) * zoom
        val ox = fitOffsetX(geo, w, k) + pan.x
        val oy = fitOffsetY(geo, h, k) + pan.y
        fun sx(p: ChartPos) = (ox + geo.x(p) * k).toFloat()
        fun sy(p: ChartPos) = (oy + (geo.heightNm - geo.y(p)) * k).toFloat()
        fun at(p: ChartPos) = Offset(sx(p), sy(p))
        textPaint.textSize = 11f * density
        namePaint.textSize = 12f * density
        aidPaint.textSize = 11f * density
        rulePaint.textSize = 13f * density

        drawGraticule(chart, ::sx, ::sy, textPaint)

        chart.land.forEach { land ->
            val path = Path()
            land.ring.forEachIndexed { i, p -> if (i == 0) path.moveTo(sx(p), sy(p)) else path.lineTo(sx(p), sy(p)) }
            path.close()
            drawPath(path, LandFill)
            drawPath(path, LandEdge, style = Stroke(width = 1.5f * density))
            val c = land.ring.let { r -> ChartPos(r.map { it.lat }.average(), r.map { it.lon }.average()) }
            drawLabel(land.name, sx(c), sy(c), namePaint)
        }

        chart.soundings.forEach { s ->
            drawLabel(depthText(s.depthM), sx(s.position), sy(s.position), textPaint, centered = true)
        }

        chart.hazards.forEach { hz ->
            val x = sx(hz.position)
            val y = sy(hz.position)
            val r = 7f * density
            when (hz.kind) {
                "drying-rock" -> {
                    drawLine(ChartInk, Offset(x - r, y), Offset(x + r, y), strokeWidth = 1.5f * density)
                    drawLine(ChartInk, Offset(x, y - r), Offset(x, y + r), strokeWidth = 1.5f * density)
                    hz.dryingHeightM?.let { drawLabel(depthText(it), x + r, y - r, textPaint, underline = true) }
                }
                else -> {
                    drawCircle(
                        ChartInk, radius = r * 1.6f, center = Offset(x, y),
                        style = Stroke(width = 1.2f * density, pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f * density, 4f * density))),
                    )
                    hz.depthM?.let { drawLabel(depthText(it), x, y, textPaint, centered = true) }
                }
            }
            drawLabel(hz.name, x + r * 1.8f, y + r * 2.4f, namePaint)
        }

        chart.aids.forEach { aid ->
            val x = sx(aid.position)
            val y = sy(aid.position)
            val r = 5f * density
            when (aid.kind) {
                "light" -> {
                    drawCircle(Magenta, radius = r * 0.6f, center = Offset(x, y))
                    drawCircle(Magenta, radius = r * 1.8f, center = Offset(x, y), style = Stroke(width = 1.2f * density))
                }
                "beacon" -> {
                    val tri = Path().apply { moveTo(x, y - r); lineTo(x + r, y + r); lineTo(x - r, y + r); close() }
                    drawPath(tri, ChartInk)
                }
                else -> {
                    val d = Path().apply { moveTo(x, y - r * 1.3f); lineTo(x + r, y); lineTo(x, y + r * 1.3f); lineTo(x - r, y); close() }
                    drawPath(d, Color.White)
                    drawPath(d, Magenta, style = Stroke(width = 1.5f * density))
                }
            }
            drawLabel(aid.name, x + r * 2.2f, y - r, aidPaint)
            aid.characteristic?.let { drawLabel(it, x + r * 2.2f, y + r * 1.8f, aidPaint) }
        }

        chart.compassRoses.forEach { r -> drawRose(r.position, variationEast, k, ::sx, ::sy, textPaint) }

        // The learner's plot, in pencil colours.
        val pencil = 2f * density
        plot.lines.forEach { line ->
            when (line.kind) {
                LineKind.Measured -> drawLine(PencilRed, at(line.from), at(line.to), strokeWidth = pencil)
                LineKind.Course -> {
                    drawLine(CourseBlue, at(line.from), at(line.to), strokeWidth = pencil)
                    drawArrowHead(at(line.from), at(line.to), CourseBlue, 9f * density, pencil)
                }
                LineKind.PositionLine -> drawLine(LineGreen, at(line.from), at(line.to), strokeWidth = pencil)
            }
        }

        // Parallel rule: the latest measured line walked across to the rose, with the bearing read off the ring.
        val last = plot.lastMeasured
        if (last != null && rose != null) {
            ChartPlot.parallelThrough(geo, last.from, last.to, rose, halfNm = 0.65)?.let { (p, q) ->
                drawLine(
                    Magenta.copy(alpha = 0.8f), at(p), at(q), strokeWidth = 3f * density,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f * density, 5f * density)),
                )
                val t = geo.bearingTrue(last.from, last.to)
                drawLabel("${threeFigures(t)}°T", sx(q) + 4f * density, sy(q), rulePaint)
            }
        }

        plot.points.forEach { p -> drawCircle(PencilRed, radius = 4f * density, center = at(p)) }
        plot.fixes.forEach { f ->
            drawCircle(ChartInk, radius = 9f * density, center = at(f), style = Stroke(width = 2f * density))
            drawCircle(ChartInk, radius = 2.5f * density, center = at(f))
        }
        plot.pending?.let { p -> drawCircle(PencilRed, radius = 7f * density, center = at(p), style = Stroke(width = pencil)) }
        plot.start?.let { s ->
            val half = 7f * density
            drawRect(
                CourseBlue,
                topLeft = Offset(sx(s) - half, sy(s) - half),
                size = androidx.compose.ui.geometry.Size(half * 2, half * 2),
                style = Stroke(width = pencil),
            )
        }
        plot.result?.let { r ->
            if (r !in plot.fixes) drawCircle(ChartInk, radius = 6f * density, center = at(r), style = Stroke(width = 1.5f * density))
        }

        cursor?.let { c ->
            val x = sx(c)
            val y = sy(c)
            drawLine(PencilRed.copy(alpha = 0.5f), Offset(x - 12f * density, y), Offset(x + 12f * density, y), strokeWidth = 1f * density)
            drawLine(PencilRed.copy(alpha = 0.5f), Offset(x, y - 12f * density), Offset(x, y + 12f * density), strokeWidth = 1f * density)
        }
    }
}

private fun DrawScope.drawArrowHead(from: Offset, to: Offset, color: Color, size: Float, width: Float) {
    val d = to - from
    val len = d.getDistance()
    if (len < 1f) return
    val ux = d.x / len
    val uy = d.y / len
    val back = Offset(to.x - ux * size, to.y - uy * size)
    drawLine(color, to, Offset(back.x - uy * size * 0.5f, back.y + ux * size * 0.5f), strokeWidth = width)
    drawLine(color, to, Offset(back.x + uy * size * 0.5f, back.y - ux * size * 0.5f), strokeWidth = width)
}

private fun clampPan(pan: Offset, zoom: Float, w: Float, h: Float): Offset {
    // Allow the chart to slide until its edge reaches the middle of the view.
    val maxX = w * zoom / 2f
    val maxY = h * zoom / 2f
    return Offset(pan.x.coerceIn(-maxX, maxX), pan.y.coerceIn(-maxY, maxY))
}

private fun baseScale(geo: ChartGeometry, w: Float, h: Float): Float =
    min(w / geo.widthNm.toFloat(), h / geo.heightNm.toFloat())

private fun fitOffsetX(geo: ChartGeometry, w: Float, k: Float): Float = (w - geo.widthNm.toFloat() * k) / 2f
private fun fitOffsetY(geo: ChartGeometry, h: Float, k: Float): Float = (h - geo.heightNm.toFloat() * k) / 2f

private fun depthText(m: Double): String = if (m == Math.rint(m)) m.toLong().toString() else "%.1f".format(java.util.Locale.US, m)

private fun DrawScope.drawLabel(
    text: String, x: Float, y: Float, paint: Paint, centered: Boolean = false, underline: Boolean = false,
) {
    drawIntoCanvas { canvas ->
        val width = paint.measureText(text)
        val left = if (centered) x - width / 2f else x
        val base = if (centered) y + paint.textSize * 0.35f else y
        canvas.nativeCanvas.drawText(text, left, base, paint)
        if (underline) canvas.nativeCanvas.drawLine(left, base + 3f, left + width, base + 3f, paint)
    }
}

/** Faint lines every minute of latitude and longitude, labelled at the sheet edges. */
private fun DrawScope.drawGraticule(
    chart: Chart,
    sx: (ChartPos) -> Float,
    sy: (ChartPos) -> Float,
    paint: Paint,
) {
    val e = chart.extent
    val stepDeg = 1.0 / 60.0
    var lat = Math.ceil(e.south / stepDeg) * stepDeg
    while (lat <= e.north + 1e-9) {
        val a = ChartPos(lat, e.west)
        val b = ChartPos(lat, e.east)
        drawLine(GridInk, Offset(sx(a), sy(a)), Offset(sx(b), sy(b)), strokeWidth = 1f)
        drawLabel(ChartGeometry.formatLat(lat), sx(a) + 4f, sy(a) - 3f, paint)
        lat += stepDeg
    }
    var lon = Math.ceil(e.west / stepDeg) * stepDeg
    while (lon <= e.east + 1e-9) {
        val a = ChartPos(e.south, lon)
        val b = ChartPos(e.north, lon)
        drawLine(GridInk, Offset(sx(a), sy(a)), Offset(sx(b), sy(b)), strokeWidth = 1f)
        drawLabel(ChartGeometry.formatLon(lon), sx(b) + 3f, sy(b) + paint.textSize + 2f, paint)
        lon += stepDeg
    }
}

/** Compass rose: outer ring true, inner ring magnetic, turned by the variation. */
private fun DrawScope.drawRose(
    position: ChartPos,
    variationEast: Double,
    k: Float,
    sx: (ChartPos) -> Float,
    sy: (ChartPos) -> Float,
    paint: Paint,
) {
    val cx = sx(position)
    val cy = sy(position)
    val outer = (0.55 * k).toFloat()
    val inner = outer * 0.72f
    val v = variationEast.toFloat()
    drawCircle(ChartInk, radius = outer, center = Offset(cx, cy), style = Stroke(width = 1.2f))
    drawCircle(Magenta, radius = inner, center = Offset(cx, cy), style = Stroke(width = 1.2f))
    for (deg in 0 until 360 step 10) {
        val major = deg % 30 == 0
        drawTick(cx, cy, outer, deg.toFloat(), if (major) 0.14f else 0.07f, ChartInk)
        drawTick(cx, cy, inner, deg.toFloat() + v, if (major) 0.14f else 0.07f, Magenta)
    }
    drawLine(ChartInk, Offset(cx, cy - outer * 1.08f), Offset(cx, cy + outer * 0.9f), strokeWidth = 1f)
    drawLabel("N", cx, cy - outer * 1.12f, paint, centered = true)
}

private fun DrawScope.drawTick(cx: Float, cy: Float, radius: Float, deg: Float, length: Float, color: Color) {
    val r = Math.toRadians(deg.toDouble())
    val s = Math.sin(r).toFloat()
    val c = Math.cos(r).toFloat()
    drawLine(
        color,
        Offset(cx + s * radius, cy - c * radius),
        Offset(cx + s * radius * (1f - length), cy - c * radius * (1f - length)),
        strokeWidth = 1f,
    )
}
