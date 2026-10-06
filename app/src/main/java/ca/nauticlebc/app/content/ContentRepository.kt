package ca.nauticlebc.app.content

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Loads the bundled content. The bundle is generated from /content by tools/build_bundle.py at build time. */
object ContentRepository {
    private const val BUNDLE_ASSET = "content/bundle.json"

    @Volatile
    private var cached: ContentBundle? = null

    suspend fun load(context: Context): Result<ContentBundle> = withContext(Dispatchers.IO) {
        cached?.let { return@withContext Result.success(it) }
        runCatching {
            val text = context.applicationContext.assets.open(BUNDLE_ASSET).bufferedReader().use { it.readText() }
            ContentBundle.parse(text)
        }.onSuccess { cached = it }
    }
}
