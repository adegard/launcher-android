// LauncherApp.kt
package com.example.launcherforyoungs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun LauncherApp() {
    val context = LocalContext.current

    // Main launcher screen
    LauncherScreen(
        onChatClick = { openMessagingApp(it) },
        onListenClick = { openMusicApp(it) },
        onMapClick = { openMapApp(it) },
        onTakePhotoClick = { openPhotosApp(it) },
        onScheduleClick = { openCalendarApp(it) },
        onMoreClick = { openAppDrawer(it) },
        onSettingsClick = { openSettings(it) },
        onDownArrowClick = { /* Handle down arrow click if needed */ }
    )
}