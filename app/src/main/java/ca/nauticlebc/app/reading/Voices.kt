package ca.nauticlebc.app.reading

/** One installed speech voice, stripped of Android types so the choice can be tested. */
data class VoiceChoice(
    val name: String,
    val quality: Int,
    val languageTag: String,
    val needsNetwork: Boolean,
    val installed: Boolean,
)

/**
 * The best English voice that stays on the phone. A network voice would send the lesson off the
 * device, so it is never chosen. Canadian English wins, then British, Australian and American.
 */
fun bestOfflineEnglishVoice(voices: Collection<VoiceChoice>): VoiceChoice? =
    voices
        .filter { it.installed && !it.needsNetwork && it.languageTag.startsWith("en", ignoreCase = true) }
        .maxWithOrNull(compareBy<VoiceChoice> { it.quality }.thenBy { englishLocaleRank(it.languageTag) })

internal fun englishLocaleRank(tag: String): Int {
    val t = tag.lowercase().replace('_', '-')
    return when {
        t.startsWith("en-ca") -> 4
        t.startsWith("en-gb") -> 3
        t.startsWith("en-au") -> 2
        t.startsWith("en-us") -> 1
        else -> 0
    }
}
