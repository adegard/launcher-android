// MainActivity.kt
package com.example.launcherforyoungs

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.launcherapp.ui.theme.LauncherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LauncherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.background) {
                    LauncherApp()
                }
            }
        }
    }
    
    // Handle home button to ensure our launcher is shown when home is pressed
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.action == Intent.ACTION_MAIN) {
            if (intent.hasCategory(Intent.CATEGORY_HOME)) {
                // Home button was pressed
                // We're already at the launcher, so nothing needs to be done
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LauncherAppTheme {
        LauncherApp()
    }
}