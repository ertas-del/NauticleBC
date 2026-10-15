package ca.nauticlebc.app.content

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.hypot

/**
 * Flat chart maths for a small area: a minute of latitude is one nautical mile and a minute of
 * longitude is cos(latitude) nautical miles. Matches tools/chartlib.py so answers agree with the data.
 * Chart x runs east and y runs north, both in nautical miles from the south-west corner.
 */
class ChartGeometry(private val extent: ChartExtent) {
    private val cosLat = cos(Math.toRadians((extent.south + extent.north) / 2))

    val widthNm: Double get() = (extent.east - extent.west) * NM_PER_DEG * cosLat
    val heightNm: Double get() = (extent.north - extent.south) * NM_PER_DEG

    fun x(p: ChartPos): Double = (p.lon - extent.west) * NM_PER_DEG * cosLat
    fun y(p: ChartPos): Double = (p.lat - extent.south) * NM_PER_DEG

    fun position(xNm: Double, yNm: Double) =
        ChartPos(extent.south + yNm / NM_PER_DEG, extent.west + xNm / (NM_PER_DEG * cosLat))

    fun bearingTrue(from: ChartPos, to: ChartPos): Double {
        val deg = Math.toDegrees(atan2(x(to) - x(from), y(to) - y(from)))
        return (deg + 360.0) % 360.0
    }

    fun distanceNm(a: ChartPos, b: ChartPos): Double = hypot(x(b) - x(a), y(b) - y(a))

    companion object {
        const val NM_PER_DEG = 60.0

        /** "33°02.6′N" style text. */
        fun formatLat(lat: Double): String = format(lat, "N", "S")
        fun formatLon(lon: Double): String = format(lon, "E", "W")

        private fun format(value: Double, pos: String, neg: String): String {
            val a = kotlin.math.abs(value)
            var deg = a.toInt()
            var min = Math.round((a - deg) * 600.0) / 10.0
            if (min >= 60.0) { deg += 1; min = 0.0 }
            return "%d°%04.1f′%s".format(java.util.Locale.US, deg, min, if (value >= 0) pos else neg)
        }
    }
}
