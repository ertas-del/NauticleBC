package ca.nauticlebc.app.reading

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.nauticlebc.app.markdown.MdText
import java.util.Locale

/** Reads text aloud with the phone's own text-to-speech engine. Nothing is sent anywhere. */
class ReadAloud(context: Context) {
    var speaking by mutableStateOf(false)
        private set
    var unavailable by mutableStateOf(false)
        private set

    private var ready = false
    private var pending: Pair<List<MdText.SpeechItem>, Float>? = null
    private var remaining = 0
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val engine = tts
                if (engine != null) {
                    engine.language = Locale.CANADA
                    engine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(utteranceId: String?) {}
                        override fun onDone(utteranceId: String?) = finishOne()
                        @Deprecated("Deprecated in Java")
                        override fun onError(utteranceId: String?) = finishOne()
                    })
                    ready = true
                    pending?.let { (items, rate) -> speak(items, rate) }
                    pending = null
                }
            } else {
                unavailable = true
                speaking = false
            }
        }
    }

    private fun finishOne() {
        remaining -= 1
        if (remaining <= 0) speaking = false
    }

    fun speak(items: List<MdText.SpeechItem>, rate: Float) {
        if (items.isEmpty()) return
        if (!ready) {
            pending = items to rate
            speaking = true
            return
        }
        val engine = tts ?: return
        engine.stop()
        engine.setSpeechRate(rate)
        remaining = 0
        speaking = true
        items.forEachIndexed { i, item ->
            engine.speak(item.text, TextToSpeech.QUEUE_ADD, null, "item-$i")
            remaining += 1
            if (item.pauseMs > 0) {
                engine.playSilentUtterance(item.pauseMs.toLong(), TextToSpeech.QUEUE_ADD, "pause-$i")
                remaining += 1
            }
        }
    }

    fun stop() {
        pending = null
        tts?.stop()
        remaining = 0
        speaking = false
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        speaking = false
    }
}
