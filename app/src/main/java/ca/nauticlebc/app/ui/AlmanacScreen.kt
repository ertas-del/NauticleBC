package ca.nauticlebc.app.ui

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
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.almanac.Almanac
import ca.nauticlebc.app.almanac.AlmanacPage
import ca.nauticlebc.app.almanac.SunFix
import kotlin.random.Random

/** One invented Sun sight from a daily page. The working is shown after the answer is checked. */
@Composable
fun AlmanacScreen(onBack: () -> Unit) {
    var seed by rememberSaveable { mutableStateOf(Random.nextLong()) }
    var input by rememberSaveable { mutableStateOf("") }
    var checked by rememberSaveable { mutableStateOf(false) }
    var correct by rememberSaveable { mutableStateOf(false) }
    var badInput by rememberSaveable { mutableStateOf(false) }
    val fix = remember(seed) { Almanac.question(Random(seed)) }
    val nextHour = remember(fix) { Almanac.nextHour(fix.page) }

    fun check() {
        val parsed = if (fix.askGha) Almanac.parseGha(input) else Almanac.parseDec(input)
        if (parsed == null) {
            badInput = true
            return
        }
        badInput = false
        correct = parsed == fix.answerTenths
        checked = true
    }

    NauticleScaffold(title = stringResource(R.string.almanac_screen_title), onBack = onBack) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = stringResource(R.string.almanac_banner),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.error,
            )
            PageCard(fix.page, nextHour)
            Text(stringResource(R.string.almanac_method), style = MaterialTheme.typography.bodyLarge)
            Text(
                text = stringResource(
                    if (fix.askGha) R.string.almanac_ask_gha else R.string.almanac_ask_dec,
                    fix.page.hour,
                    fix.minutes,
                ),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.semantics { heading() },
            )
            OutlinedTextField(
                value = input,
                onValueChange = { input = it; badInput = false },
                enabled = !checked,
                label = { Text(stringResource(if (fix.askGha) R.string.almanac_gha_hint else R.string.almanac_dec_hint)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )
            if (badInput) {
                Text(stringResource(R.string.almanac_bad_input), style = MaterialTheme.typography.bodyLarge)
            }
            if (!checked) {
                Button(onClick = { check() }, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.almanac_check))
                }
            } else {
                Text(
                    text = stringResource(if (correct) R.string.almanac_correct else R.string.almanac_incorrect),
                    style = MaterialTheme.typography.titleMedium,
                )
                Working(fix)
                Button(
                    onClick = {
                        seed = Random.nextLong()
                        input = ""
                        checked = false
                        badInput = false
                    },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                ) { Text(stringResource(R.string.almanac_next)) }
            }
        }
    }
}

@Composable
private fun PageCard(page: AlmanacPage, next: AlmanacPage) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(stringResource(R.string.almanac_sun), style = MaterialTheme.typography.titleMedium)
            Text(stringResource(R.string.almanac_columns), style = MaterialTheme.typography.labelLarge)
            PageLine(page, showD = true)
            PageLine(next, showD = false)
        }
    }
}

@Composable
private fun PageLine(page: AlmanacPage, showD: Boolean) {
    val gha = Almanac.arcParts(page.ghaTenths)
    val dec = Almanac.arcParts(page.decTenths)
    val decName = stringResource(if (page.decTenths >= 0) R.string.almanac_north else R.string.almanac_south)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("%02d".format(page.hour), fontFamily = FontFamily.Monospace, modifier = Modifier.weight(0.6f))
        Text(
            stringResource(R.string.almanac_arc, gha.degrees, gha.minutes),
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.weight(1.4f),
        )
        Text(
            stringResource(R.string.almanac_dec_arc, decName, dec.degrees, dec.minutes),
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.weight(1.6f),
        )
        Text(
            if (!showD) "" else stringResource(
                R.string.almanac_d,
                Almanac.dText(page.dTenths),
                stringResource(if (page.dTenths >= 0) R.string.almanac_north else R.string.almanac_south),
            ),
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun Working(fix: SunFix) {
    val ghaHour = Almanac.arcParts(fix.page.ghaTenths)
    val gha = Almanac.arcParts(fix.ghaTenths)
    val inc = Almanac.arcParts(fix.incrementTenths)
    val decHour = Almanac.arcParts(fix.page.decTenths)
    val dec = Almanac.arcParts(fix.decTenths)
    val corr = Almanac.arcParts(fix.correctionTenths)
    val decName = stringResource(if (fix.page.decTenths >= 0) R.string.almanac_north else R.string.almanac_south)
    val decWay = stringResource(if (fix.page.dTenths >= 0) R.string.almanac_north else R.string.almanac_south)
    val answerName = stringResource(if (fix.decTenths >= 0) R.string.almanac_north else R.string.almanac_south)
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        if (fix.askGha) {
            Text(stringResource(R.string.almanac_step_gha_hour, fix.page.hour, ghaHour.degrees, ghaHour.minutes))
            Text(stringResource(R.string.almanac_step_increment, fix.minutes, inc.degrees, inc.minutes))
            Text(stringResource(R.string.almanac_step_gha, gha.degrees, gha.minutes))
        } else {
            Text(stringResource(R.string.almanac_step_dec_hour, fix.page.hour, decName, decHour.degrees, decHour.minutes))
            Text(stringResource(R.string.almanac_step_d, Almanac.dText(fix.page.dTenths), decWay))
            Text(stringResource(R.string.almanac_step_correction, fix.minutes, Almanac.dText(fix.correctionTenths)))
            Text(stringResource(R.string.almanac_step_dec, answerName, dec.degrees, dec.minutes))
        }
    }
}
