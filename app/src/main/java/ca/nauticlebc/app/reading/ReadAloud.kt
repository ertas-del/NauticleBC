package ca.nauticlebc.app.reading

import android.content.Context
import android.media.AudioAttributes
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.speech.tts.Voice
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ca.nauticlebc.app.markdown.MdText
import java.util.Locale

/** Reads a lesson with the best English voice already on the phone. Nothing is sent anywhere. */
class ReadAloud(context: Context) {
    var speaking by mutableStateOf(false)
        private set
    var unavailable by mutableStateOf(false)
        private set

    private val main = Handler(Looper.getMainLooper())
    private var ready = false
    private var pending: Pair<List<MdText.SpeechItem>, Float>? = null
    private var remaining = 0
    private var generation = 0
    private val finished = mutableSetOf<String>()
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            val engine = tts
            if (status != TextToSpeech.SUCCESS || engine == null) {
                unavailable = true
                speaking = false
                return@TextToSpeech
            }
            engine.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build(),
            )
            applyBestVoice(engine)
            engine.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {}
                override fun onDone(utteranceId: String?) = finish(utteranceId)
                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) = finish(utteranceId)
                override fun onError(utteranceId: String?, errorCode: Int) = finish(utteranceId)
            })
            ready = true
            pending?.let { (items, rate) -> speak(items, rate) }
            pending = null
        }
    }

    private fun applyBestVoice(engine: TextToSpeech) {
        val voices = engine.voices.orEmpty()
        val chosen = bestOfflineEnglishVoice(voices.map { it.toChoice() }) ?: return
        voices.firstOrNull { it.name == chosen.name }?.let { engine.voice = it }
    }

    private fun finish(utteranceId: String?) {
        val id = utteranceId ?: return
        val gen = id.substringBefore(':').toIntOrNull()
        main.post {
            if (gen != generation || !finished.add(id)) return@post
            remaining -= 1
            if (remaining <= 0) speaking = false
        }
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
        generation += 1
        finished.clear()
        engine.setSpeechRate(rate.coerceIn(0.5f, 1.5f))
        remaining = 0
        speaking = true
        val gen = generation
        items.forEachIndexed { i, item ->
            val id = "$gen:item-$i"
            engine.speak(item.text, TextToSpeech.QUEUE_ADD, null, id)
            remaining += 1
            if (item.pauseMs > 0) {
                val pauseId = "$gen:pause-$i"
                engine.playSilentUtterance(item.pauseMs.toLong(), TextToSpeech.QUEUE_ADD, pauseId)
                remaining += 1
            }
        }
    }

    fun stop() {
        generation += 1
        pending = null
        tts?.stop()
        remaining = 0
        speaking = false
    }

    fun shutdown() {
        generation += 1
        tts?.stop()
        tts?.shutdown()
        tts = null
        speaking = false
    }
}

private fun Voice.toChoice(): VoiceChoice = VoiceChoice(
    name = name,
    quality = quality,
    languageTag = locale.toLanguageTag(),
    needsNetwork = isNetworkConnectionRequired,
    installed = features?.contains(TextToSpeech.Engine.KEY_FEATURE_NOT_INSTALLED) != true,
)
