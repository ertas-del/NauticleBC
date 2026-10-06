package ca.nauticlebc.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ca.nauticlebc.app.ui.HomeScreen
import ca.nauticlebc.app.ui.NauticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NauticleTheme {
                HomeScreen()
            }
        }
    }
}
