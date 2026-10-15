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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ca.nauticlebc.app.R
import ca.nauticlebc.app.content.Chart
import ca.nauticlebc.app.content.ChartExercise
import ca.nauticlebc.app.content.ChartGeometry
import ca.nauticlebc.app.content.ChartPos
import ca.nauticlebc.app.content.ContentBundle
import kotlin.math.min

private val Water = Color(0xFFE6F1F6)
private val LandFill = Color(0xFFF2E7BC)
private val LandEdge = Color(0xFF6B5B2A)
private val ChartInk = Color(0xFF1F2A33)
private val Magenta = Color(0xFFA6246F)
private val GridInk = Color(0x33355A70)

/** Practice chart: a fictional chart drawn from data, with pan, zoom, a position readout and the exercise list. */
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

    var exerciseIndex by rememberSaveable { mutableStateOf(0) }
    var showMethod by rememberSaveable(exerciseIndex) { mutableStateOf(false) }
    var showAnswer by rememberSaveable(exerciseIndex) { mutableStateOf(false) }
    var zoom by remember { mutableFloatStateOf(1f) }
    var pan by remember { mutableStateOf(Offset.Zero) }
    var cursor by remember { mutableStateOf<ChartPos?>(null) }

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
                onTransform = { z, p -> zoom = z; pan = p },
                onTap = { cursor = it },
                modifier = Modifier.weight(1f).fillMaxWidth(),
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val c = cursor
                Text(
                    text = if (c == null) stringResource(R.string.chart_tap_hint)
                    else stringResource(
                        R.string.chart_cursor, ChartGeometry.formatLat(c.lat), ChartGeometry.formatLon(c.lon),
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                )
                OutlinedButton(onClick = { zoom = 1f; pan = Offset.Zero; cursor = null }) {
                    Text(stringResource(R.string.chart_reset))
                }
            }
            ExercisePanel(
                chart = chart,
                exercises = exercises,
                index = exerciseIndex,
                onIndex = { exerciseIndex = it },
                showMethod = showMethod,
                onShowMethod = { showMethod = it },
                showAnswer = showAnswer,
                onShowAnswer = { showAnswer = it },
                modifier = Modifier.fillMaxWidth().heightIn(max = 260.dp),
            )
        }
    }
}

@Composable
private fun ExercisePanel(
    chart: Chart,
    exercises: List<ChartExercise>,
    index: Int,
    onIndex: (Int) -> Unit,
    showMethod: Boolean,
    onShowMethod: (Boolean) -> Unit,
    showAnswer: Boolean,
    onShowAnswer: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.surfaceVariant).verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (exercises.isEmpty()) {
            Text(stringResource(R.string.chart_no_exercises), style = MaterialTheme.typography.bodyMedium)
            return@Column
        }
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
        val ex = exercises[index.coerceIn(0, exercises.lastIndex)]
        Text(ex.prompt, style = MaterialTheme.typography.bodyLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { onShowMethod(!showMethod) }) {
                Text(stringResource(if (showMethod) R.string.chart_hide_method else R.string.chart_show_method))
            }
            Button(onClick = { onShowAnswer(!showAnswer) }) {
                Text(stringResource(if (showAnswer) R.string.chart_hide_answer else R.string.chart_show_answer))
            }
        }
        if (showMethod) {
            ex.method.forEachIndexed { i, step ->
                Text("${i + 1}. $step", style = MaterialTheme.typography.bodyMedium)
            }
        }
        if (showAnswer) {
            ex.answers.forEach { a ->
                val pos = a.position
                val shown = if (pos != null) {
                    "${ChartGeometry.formatLat(pos.lat)} ${ChartGeometry.formatLon(pos.lon)}"
                } else {
                    formatNumber(a.number ?: 0.0) + (a.unit?.let { " $it" } ?: "")
                }
                val tol = a.tolerance?.let { stringResource(R.string.chart_tolerance, formatNumber(it)) }.orEmpty()
                Text("${a.label}: $shown $tol", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Text(chart.areaNote, style = MaterialTheme.typography.bodySmall)
    }
}

private fun formatNumber(v: Double): String =
    if (v == Math.rint(v)) v.toLong().toString() else "%.2f".format(java.util.Locale.US, v).trimEnd('0').trimEnd('.')

@Composable
private fun ChartCanvas(
    chart: Chart,
    geo: ChartGeometry,
    zoom: Float,
    pan: Offset,
    cursor: ChartPos?,
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
    Canvas(
        modifier = modifier
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
            .pointerInput(geo, zoom, pan) {
                detectTapGestures { tap ->
                    val k = baseScale(geo, size.width.toFloat(), size.height.toFloat()) * zoom
                    val ox = fitOffsetX(geo, size.width.toFloat(), k) + pan.x
                    val oy = fitOffsetY(geo, size.height.toFloat(), k) + pan.y
                    val xNm = ((tap.x - ox) / k).toDouble()
                    val yNm = geo.heightNm - ((tap.y - oy) / k).toDouble()
                    onTap(geo.position(xNm, yNm))
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
        textPaint.textSize = 11f * density
        namePaint.textSize = 12f * density
        aidPaint.textSize = 11f * density

        // Chart sheet edge.
        drawRect(
            color = Water,
            topLeft = Offset(ox.toFloat(), oy.toFloat()),
            size = androidx.compose.ui.geometry.Size((geo.widthNm * k).toFloat(), (geo.heightNm * k).toFloat()),
        )
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
                        style = Stroke(width = 1.2f * density, pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(4f * density, 4f * density))),
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

        chart.compassRoses.forEach { rose -> drawRose(rose.position, chart, k, ::sx, ::sy, textPaint) }

        cursor?.let { c ->
            val x = sx(c)
            val y = sy(c)
            drawLine(Color(0xFFD32F2F), Offset(x - 12f * density, y), Offset(x + 12f * density, y), strokeWidth = 1.5f * density)
            drawLine(Color(0xFFD32F2F), Offset(x, y - 12f * density), Offset(x, y + 12f * density), strokeWidth = 1.5f * density)
        }
    }
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
    chart: Chart,
    k: Float,
    sx: (ChartPos) -> Float,
    sy: (ChartPos) -> Float,
    paint: Paint,
) {
    val cx = sx(position)
    val cy = sy(position)
    val outer = (0.55 * k).toFloat()
    val inner = outer * 0.72f
    val v = chart.variation.let {
        val d = it.degrees + it.minutes / 60.0 + it.annualChangeMinutes / 60.0 * (2026 - it.year)
        if (it.direction == "E") d else -d
    }.toFloat()
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
