package ca.nauticlebc.app.progress

import android.content.Context

/** Stores per-lesson completion and best quiz score in a small SharedPreferences file. */
class SharedPreferencesProgressStore(context: Context) : ProgressStore {
    private val prefs = context.applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override fun load(): Map<String, LessonProgress> = ProgressCodec.decodeAll(prefs.all)

    override fun save(lessonId: String, progress: LessonProgress) {
        val editor = prefs.edit()
        editor.putBoolean(ProgressCodec.doneKey(lessonId), progress.completed)
        val best = progress.best
        if (best != null) {
            editor.putString(ProgressCodec.bestKey(lessonId), ProgressCodec.encodeBest(best))
        } else {
            editor.remove(ProgressCodec.bestKey(lessonId))
        }
        editor.apply()
    }

    private companion object {
        const val FILE_NAME = "lesson_progress"
    }
}
