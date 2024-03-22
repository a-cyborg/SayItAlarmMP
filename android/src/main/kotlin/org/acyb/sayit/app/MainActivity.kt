package org.acyb.sayit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import org.acyb.sayit.app.token.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        setContent {
            Color.isDarkTheme(isSystemInDarkTheme())

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.surface.standard,
            ) {

            }
        }
    }
}