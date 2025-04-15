// LauncherScreen.kt
package com.example.launcherforyoungs

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun LauncherScreen(
    onChatClick: (Context) -> Unit,
    onListenClick: (Context) -> Unit,
    onMapClick: (Context) -> Unit,
    onTakePhotoClick: (Context) -> Unit,
    onScheduleClick: (Context) -> Unit,
    onDownArrowClick: () -> Unit,
    onMoreClick: (Context) -> Unit,
    onSettingsClick: (Context) -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            // Current time display
            CurrentTimeDisplay(
                modifier = Modifier
                    .padding(16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            MenuLink(text = "Chat", onClick = { onChatClick(context) })
            Spacer(modifier = Modifier.height(24.dp))
            MenuLink(text = "Listen", onClick = { onListenClick(context) })
            Spacer(modifier = Modifier.height(24.dp))
            MenuLink(text = "Map", onClick = { onMapClick(context) })
            Spacer(modifier = Modifier.height(24.dp))
            MenuLink(text = "Photo", onClick = { onTakePhotoClick(context) })
            Spacer(modifier = Modifier.height(24.dp))
            MenuLink(text = "Schedule", onClick = { onScheduleClick(context) })
            Spacer(modifier = Modifier.height(32.dp))
            MenuLink(text = "More", onClick = { onMoreClick(context) }, fontSize = 18)
            Spacer(modifier = Modifier.height(12.dp))
            MenuLink(text = "Settings", onClick = { onSettingsClick(context) }, fontSize = 18)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Scroll Down",
                modifier = Modifier
                    .clickable { onDownArrowClick() }
                    .padding(8.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun CurrentTimeDisplay(modifier: Modifier = Modifier) {
    var currentTime by remember { mutableStateOf(getCurrentTimeFormatted()) }

    // Update time every minute
    LaunchedEffect(Unit) {
        while(true) {
            currentTime = getCurrentTimeFormatted()
            delay(60000) // Update every minute
        }
    }

    Text(
        text = currentTime,
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        modifier = modifier
    )
}

private fun getCurrentTimeFormatted(): String {
    val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
    return sdf.format(Date())
}


@Composable
fun MenuLink(text: String, onClick: () -> Unit, fontSize: Int = 28) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        ),
        textAlign = TextAlign.Start,
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

// Intent functions to launch different apps
fun openMessagingApp(context: Context) {
    // Open the default SMS/messaging app
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_APP_MESSAGING)
    context.startActivity(intent)
}

fun openPhotosApp(context: Context) {
    // Open the default gallery/photos app
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_APP_GALLERY)
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        // Fallback to view images
        val viewIntent = Intent(Intent.ACTION_VIEW)
        viewIntent.setType("image/*")
        context.startActivity(viewIntent)
    }
}

fun openCalendarApp(context: Context) {
    // Open Google Calendar
    try {
        val calendarIntent = Intent(Intent.ACTION_VIEW)
        calendarIntent.data = Uri.parse("content://com.android.calendar/time")
        context.startActivity(calendarIntent)
    } catch (e: Exception) {
        // Fallback to calendar view intent
        val intent = Intent(Intent.ACTION_INSERT)
        intent.setData(CalendarContract.Events.CONTENT_URI)
        context.startActivity(intent)
    }
}

fun openSettings(context: Context) {
    val intent = Intent(Settings.ACTION_SETTINGS)
    context.startActivity(intent)
}

fun openAppDrawer(context: Context) {
    // This opens the app drawer/all apps screen
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    context.startActivity(intent)
}

fun openMapApp(context: Context) {
    try {
        // Try to open Google Maps first
        val gmmIntentUri = Uri.parse("geo:0,0?q=")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        // Check if Google Maps is installed
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
        } else {
            // If Google Maps isn't available, open any maps app
            val defaultMapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            context.startActivity(defaultMapIntent)
        }
    } catch (e: Exception) {
        // Fallback in case of any errors
        val defaultMapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="))
        context.startActivity(defaultMapIntent)
    }
}

fun openMusicApp(context: Context) {
    // Opens the music app
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_APP_MUSIC)
    context.startActivity(intent)
}

@Preview(showBackground = true, name = "Launcher Screen Preview")
@Composable
fun LauncherScreenPreview() {
    MaterialTheme {
        LauncherScreen(
            onChatClick = {},
            onListenClick = {},
            onMapClick = {},
            onTakePhotoClick = {},
            onScheduleClick = {},
            onMoreClick = {},
            onSettingsClick = {},
            onDownArrowClick = {}
        )
    }
}