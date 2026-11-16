package ca.nauticlebc.app.seatime

import android.content.Context

/** Keeps the sea time record in its own SharedPreferences file, on the phone only. */
class SharedPreferencesSeaTimeStore(context: Context) : SeaTimeStore {
    private val prefs = context.applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override fun read(key: String): String? = prefs.getString(key, null)

    override fun write(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    private companion object {
        const val FILE_NAME = "sea_time"
    }
}
