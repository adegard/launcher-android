// LauncherApp.kt
package com.example.launcherforyoungs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

enum class Screen {
    LAUNCHER,
    APP_LIST
}

@Composable
fun LauncherApp() {
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf(Screen.LAUNCHER) }
    
    when (currentScreen) {
        Screen.LAUNCHER -> {
            LauncherScreen(
                onChatClick = { openMessagingApp(it) },
                onListenClick = { openMusicApp(it) },
                onMapClick = { openMapApp(it) },
                onTakePhotoClick = { openPhotosApp(it) },
                onScheduleClick = { openCalendarApp(it) },
                onMoreClick = { currentScreen = Screen.APP_LIST },
                onSettingsClick = { openSettings(it) },
                onDownArrowClick = { /* Handle down arrow click if needed */ }
            )
        }
        Screen.APP_LIST -> {
            AppListScreen(
                onBackClick = { currentScreen = Screen.LAUNCHER }
            )
        }
    }
}