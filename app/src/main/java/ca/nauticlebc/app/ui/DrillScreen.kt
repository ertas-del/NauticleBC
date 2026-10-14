package ca.nauticlebc.app.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.drills.Drill
import ca.nauticlebc.app.drills.DrillArg
import ca.nauticlebc.app.drills.DrillKind
import ca.nauticlebc.app.drills.DrillText
import ca.nauticlebc.app.drills.Drills
import kotlin.random.Random

/** Turns a [DrillText] into display text, looking up East/West and add/subtract words in the string resources. */
@Composable
private fun drillString(text: DrillText): String {
    val east = stringResource(R.string.drill_east)
    val west = stringResource(R.string.drill_west)
    val add = stringResource(R.string.drill_op_add)
    val subtract = stringResource(R.string.drill_op_subtract)
    val args: Array<Any> = text.args.map { a ->
        when (a) {
            is DrillArg.Num -> a.value
            is DrillArg.Str -> a.value
            is DrillArg.Dir -> if (a.east) east else west
            is DrillArg.Op -> if (a.add) add else subtract
        }
    }.toTypedArray()
    return stringResource(text.res, *args)
}

/** Random practice sums with the working shown after each answer. No chart is needed. */
@Composable
fun DrillScreen(onBack: () -> Unit) {
    // -1 means mixed; otherwise the index of a DrillKind. Saved so rotation keeps the same sum.
    var kindIndex by rememberSaveable { mutableStateOf(-1) }
    var seed by rememberSaveable { mutableStateOf(Random.nextLong()) }
    var input by rememberSaveable { mutableStateOf("") }
    var checked by rememberSaveable { mutableStateOf(false) }
    var correct by rememberSaveable { mutableStateOf(false) }
    var notANumber by rememberSaveable { mutableStateOf(false) }
    var asked by rememberSaveable { mutableStateOf(0) }
    var right by rememberSaveable { mutableStateOf(0) }

    val kind = DrillKind.entries.getOrNull(kindIndex)
    val drill: Drill = remember(kindIndex, seed) { Drills.forSeed(kind, seed) }

    fun next() {
        seed = Random.nextLong()
        input = ""
        checked = false
        notANumber = false
    }

    NauticleScaffold(title = stringResource(R.string.drill_screen_title), onBack = onBack) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                FilterChip(
                    selected = kindIndex == -1,
                    onClick = { if (kindIndex != -1) { kindIndex = -1; next() } },
                    label = { Text(stringResource(R.string.drill_kind_mixed)) },
                )
                DrillKind.entries.forEachIndexed { i, k ->
                    FilterChip(
                        selected = kindIndex == i,
                        onClick = { if (kindIndex != i) { kindIndex = i; next() } },
                        label = { Text(stringResource(k.label)) },
                    )
                }
            }

            Text(
                text = stringResource(drill.kind.label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.semantics { heading() },
            )
            Text(text = drillString(drill.prompt), style = MaterialTheme.typography.bodyLarge)

            val unit = stringResource(drill.unit)
            OutlinedTextField(
                value = input,
                onValueChange = { input = it; notANumber = false },
                enabled = !checked,
                singleLine = true,
                label = { Text(stringResource(R.string.drill_answer_label) + " (" + unit + ")") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
            )
            if (notANumber) {
                Text(
                    text = stringResource(R.string.drill_not_a_number),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
                )
            }

            if (!checked) {
                Button(
                    onClick = {
                        val v = input.trim().replace(',', '.').toDoubleOrNull()
                        if (v == null) {
                            notANumber = true
                        } else {
                            correct = drill.isCorrect(v)
                            checked = true
                            asked += 1
                            if (correct) right += 1
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text(stringResource(R.string.drill_check)) }
            } else {
                Text(
                    text = if (correct) {
                        stringResource(R.string.drill_correct)
                    } else {
                        stringResource(R.string.drill_incorrect, Drills.formatAnswer(drill.answer), unit)
                    },
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite },
                )
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            stringResource(R.string.drill_working),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.semantics { heading() },
                        )
                        drill.steps.forEach { step ->
                            Text(drillString(step), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
                Button(onClick = { next() }, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.drill_next))
                }
            }

            Text(stringResource(R.string.drill_score, right, asked), style = MaterialTheme.typography.labelLarge)
            Text(stringResource(R.string.drill_numbers_invented), style = MaterialTheme.typography.bodySmall)
        }
    }
}
