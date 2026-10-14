package ca.nauticlebc.app.drills

import androidx.annotation.StringRes
import ca.nauticlebc.app.R
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs
import kotlin.random.Random

/** A value slotted into a drill string. Direction and operation words come from string resources at display time. */
sealed interface DrillArg {
    data class Num(val value: Number) : DrillArg
    data class Str(val value: String) : DrillArg
    data class Dir(val east: Boolean) : DrillArg
    data class Op(val add: Boolean) : DrillArg
}

/** A string resource and its arguments, so no user-facing English lives in code. */
data class DrillText(@StringRes val res: Int, val args: List<DrillArg> = emptyList())

enum class DrillKind(@StringRes val label: Int) {
    TrueToCompass(R.string.drill_kind_true_to_compass),
    CompassToTrue(R.string.drill_kind_compass_to_true),
    DepthAndClearance(R.string.drill_kind_depth),
    DryingRock(R.string.drill_kind_rock),
    RuleOfTwelfths(R.string.drill_kind_twelfths),
    SpeedDistanceTime(R.string.drill_kind_sdt),
}

/** One generated question with its answer and the working shown after the learner checks. */
data class Drill(
    val kind: DrillKind,
    val prompt: DrillText,
    val answer: Double,
    val tolerance: Double,
    val circular: Boolean,
    @StringRes val unit: Int,
    val steps: List<DrillText>,
) {
    fun isCorrect(input: Double): Boolean {
        var diff = abs(input - answer)
        if (circular) {
            diff = diff % 360.0
            diff = minOf(diff, 360.0 - diff)
        }
        return diff <= tolerance + 1e-9
    }
}

/**
 * Random practice sums that need no chart: compass conversion, depth and clearance, drying rocks, the rule of
 * twelfths and speed, distance and time. All numbers are invented. The same seed always gives the same question.
 */
object Drills {
    private val TWELFTHS_CUMULATIVE = intArrayOf(0, 1, 3, 6, 9, 11, 12)

    fun forSeed(kind: DrillKind?, seed: Long): Drill {
        val random = Random(seed)
        val chosen = kind ?: DrillKind.entries[random.nextInt(DrillKind.entries.size)]
        return generate(chosen, random)
    }

    fun generate(kind: DrillKind, random: Random): Drill = when (kind) {
        DrillKind.TrueToCompass -> trueToCompass(random)
        DrillKind.CompassToTrue -> compassToTrue(random)
        DrillKind.DepthAndClearance -> depthAndClearance(random)
        DrillKind.DryingRock -> dryingRock(random)
        DrillKind.RuleOfTwelfths -> ruleOfTwelfths(random)
        DrillKind.SpeedDistanceTime -> speedDistanceTime(random)
    }

    private fun wrap(v: Int): Int = ((v % 360) + 360) % 360

    private fun num(v: Number) = DrillArg.Num(v)

    private fun trueToCompass(random: Random): Drill {
        val t = random.nextInt(0, 360)
        val vDeg = random.nextInt(1, 26)
        val vEast = random.nextBoolean()
        val dDeg = random.nextInt(0, 9)
        val dEast = random.nextBoolean()
        val rawMag = t - (if (vEast) vDeg else -vDeg)
        val mag = wrap(rawMag)
        val rawComp = mag - (if (dEast) dDeg else -dDeg)
        val comp = wrap(rawComp)
        val steps = mutableListOf(
            DrillText(R.string.drill_step_down_memory),
            DrillText(R.string.drill_step_true_to_magnetic, listOf(num(vDeg), DrillArg.Dir(vEast), DrillArg.Op(add = !vEast), num(mag))),
            DrillText(R.string.drill_step_magnetic_to_compass, listOf(num(dDeg), DrillArg.Dir(dEast), DrillArg.Op(add = !dEast), num(comp))),
        )
        if (rawMag !in 0..359 || rawComp !in 0..359) steps += DrillText(R.string.drill_step_wrap)
        return Drill(
            DrillKind.TrueToCompass,
            DrillText(R.string.drill_prompt_true_to_compass, listOf(num(t), num(vDeg), DrillArg.Dir(vEast), num(dDeg), DrillArg.Dir(dEast))),
            comp.toDouble(), 0.5, true, R.string.drill_unit_degrees, steps,
        )
    }

    private fun compassToTrue(random: Random): Drill {
        val c = random.nextInt(0, 360)
        val dDeg = random.nextInt(0, 9)
        val dEast = random.nextBoolean()
        val vDeg = random.nextInt(1, 26)
        val vEast = random.nextBoolean()
        val rawMag = c + (if (dEast) dDeg else -dDeg)
        val mag = wrap(rawMag)
        val rawTrue = mag + (if (vEast) vDeg else -vDeg)
        val tru = wrap(rawTrue)
        val steps = mutableListOf(
            DrillText(R.string.drill_step_up_memory),
            DrillText(R.string.drill_step_compass_to_magnetic, listOf(num(dDeg), DrillArg.Dir(dEast), DrillArg.Op(add = dEast), num(mag))),
            DrillText(R.string.drill_step_magnetic_to_true, listOf(num(vDeg), DrillArg.Dir(vEast), DrillArg.Op(add = vEast), num(tru))),
        )
        if (rawMag !in 0..359 || rawTrue !in 0..359) steps += DrillText(R.string.drill_step_wrap)
        return Drill(
            DrillKind.CompassToTrue,
            DrillText(R.string.drill_prompt_compass_to_true, listOf(num(c), num(dDeg), DrillArg.Dir(dEast), num(vDeg), DrillArg.Dir(vEast))),
            tru.toDouble(), 0.5, true, R.string.drill_unit_degrees, steps,
        )
    }

    private fun depthAndClearance(random: Random): Drill {
        val charted = random.nextInt(5, 121)   // tenths of a metre
        val tide = random.nextInt(2, 51)
        val draught = random.nextInt(8, 31)
        val depth = charted + tide
        val clearance = depth - draught
        return Drill(
            DrillKind.DepthAndClearance,
            DrillText(R.string.drill_prompt_depth, listOf(num(charted / 10.0), num(tide / 10.0), num(draught / 10.0))),
            clearance / 10.0, 0.05, false, R.string.drill_unit_metres,
            listOf(
                DrillText(R.string.drill_step_depth, listOf(num(charted / 10.0), num(tide / 10.0), num(depth / 10.0))),
                DrillText(R.string.drill_step_clearance, listOf(num(depth / 10.0), num(draught / 10.0), num(clearance / 10.0))),
            ),
        )
    }

    private fun dryingRock(random: Random): Drill {
        val drying = random.nextInt(5, 31)
        val tide = drying + random.nextInt(1, 31)
        val over = tide - drying
        return Drill(
            DrillKind.DryingRock,
            DrillText(R.string.drill_prompt_rock, listOf(num(drying / 10.0), num(tide / 10.0))),
            over / 10.0, 0.05, false, R.string.drill_unit_metres,
            listOf(
                DrillText(R.string.drill_step_rock, listOf(num(tide / 10.0), num(drying / 10.0), num(over / 10.0))),
                DrillText(R.string.drill_step_rock_showing),
            ),
        )
    }

    private fun ruleOfTwelfths(random: Random): Drill {
        val lowH = random.nextInt(2, 16)               // tenths of a metre
        val rangeUnits = random.nextInt(4, 9)          // range = 0.6 m x units, so every twelfth is a clean 0.05 step
        val rangeTenths = rangeUnits * 6
        val highH = lowH + rangeTenths
        val lwHour = random.nextInt(3, 10)
        val k = random.nextInt(1, 6)
        val cumulative = TWELFTHS_CUMULATIVE[k]
        val riseHundredths = rangeUnits * 5 * cumulative   // 0.05 m x units x twelfths
        val heightHundredths = lowH * 10 + riseHundredths
        return Drill(
            DrillKind.RuleOfTwelfths,
            DrillText(
                R.string.drill_prompt_twelfths,
                listOf(DrillArg.Str(hour(lwHour)), num(lowH / 10.0), DrillArg.Str(hour(lwHour + 6)), num(highH / 10.0), DrillArg.Str(hour(lwHour + k))),
            ),
            heightHundredths / 100.0, 0.05, false, R.string.drill_unit_metres,
            listOf(
                DrillText(R.string.drill_step_range, listOf(num(highH / 10.0), num(lowH / 10.0), num(rangeTenths / 10.0))),
                DrillText(R.string.drill_step_twelfths, listOf(num(k), num(cumulative))),
                DrillText(R.string.drill_step_rise, listOf(num(rangeTenths / 10.0), num(cumulative), num(riseHundredths / 100.0))),
                DrillText(R.string.drill_step_height, listOf(num(lowH / 10.0), num(riseHundredths / 100.0), num(heightHundredths / 100.0))),
            ),
        )
    }

    private fun hour(h: Int) = "%02d:00".format(h)

    private fun plain(v: Double): String =
        BigDecimal(v).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString()

    private val MINUTES = intArrayOf(30, 36, 45, 48, 54, 60, 72, 90, 120)

    private fun speedDistanceTime(random: Random): Drill {
        val speed = random.nextInt(4, 13)
        val minutes = MINUTES[random.nextInt(MINUTES.size)]
        val hours = minutes / 60.0
        val distance = speed * minutes / 60.0
        return when (random.nextInt(3)) {
            0 -> Drill(
                DrillKind.SpeedDistanceTime,
                DrillText(R.string.drill_prompt_sdt_distance, listOf(DrillArg.Str(speed.toString()), DrillArg.Str(minutes.toString()))),
                distance, 0.05, false, R.string.drill_unit_nm,
                listOf(
                    DrillText(R.string.drill_step_sdt_time, listOf(DrillArg.Str(minutes.toString()), DrillArg.Str(plain(hours)))),
                    DrillText(R.string.drill_step_sdt_distance, listOf(DrillArg.Str(speed.toString()), DrillArg.Str(plain(hours)), DrillArg.Str(plain(distance)))),
                ),
            )
            1 -> Drill(
                DrillKind.SpeedDistanceTime,
                DrillText(R.string.drill_prompt_sdt_time, listOf(DrillArg.Str(plain(distance)), DrillArg.Str(speed.toString()))),
                minutes.toDouble(), 1.0, false, R.string.drill_unit_minutes,
                listOf(
                    DrillText(R.string.drill_step_sdt_hours, listOf(DrillArg.Str(plain(distance)), DrillArg.Str(speed.toString()), DrillArg.Str(plain(hours)))),
                    DrillText(R.string.drill_step_sdt_minutes, listOf(DrillArg.Str(plain(hours)), DrillArg.Str(minutes.toString()))),
                ),
            )
            else -> Drill(
                DrillKind.SpeedDistanceTime,
                DrillText(R.string.drill_prompt_sdt_speed, listOf(DrillArg.Str(plain(distance)), DrillArg.Str(minutes.toString()))),
                speed.toDouble(), 0.05, false, R.string.drill_unit_knots,
                listOf(
                    DrillText(R.string.drill_step_sdt_time, listOf(DrillArg.Str(minutes.toString()), DrillArg.Str(plain(hours)))),
                    DrillText(R.string.drill_step_sdt_speed, listOf(DrillArg.Str(plain(distance)), DrillArg.Str(plain(hours)), DrillArg.Str(speed.toString()))),
                ),
            )
        }
    }

    /** The answer as the learner would write it: whole numbers without decimals, otherwise up to two places. */
    fun formatAnswer(value: Double): String = plain(value)
}
