package ca.nauticlebc.app.progress

import android.content.Context

/** Stores missed-question memory in its own SharedPreferences file. */
class SharedPreferencesRecallStore(context: Context) : RecallStore {
    private val prefs = context.applicationContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    override fun load(): Map<String, RecallMemory> = RecallCodec.decodeAll(prefs.all)

    override fun save(questionId: String, memory: RecallMemory?) {
        val editor = prefs.edit()
        if (memory == null) editor.remove(RecallCodec.key(questionId))
        else editor.putString(RecallCodec.key(questionId), RecallCodec.encode(memory))
        editor.apply()
    }

    private companion object {
        const val FILE_NAME = "recall_memory"
    }
}
