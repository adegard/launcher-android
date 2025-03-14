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
fun MainMenuScreen(
    onChatClick: () -> Unit = {},
    onListenClick: () -> Unit = {},
    onMapClick: () -> Unit = {},
    onTakePhotoClick: () -> Unit = {},
    onDownArrowClick: () -> Unit = {}
) {
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
            MenuLink(text = "Chat", onClick = onChatClick)
            Spacer(modifier = Modifier.height(24.dp))
            MenuLink(text = "Listen", onClick = onListenClick)
            Spacer(modifier = Modifier.height(24.dp))
            MenuLink(text = "Map", onClick = onMapClick)
            Spacer(modifier = Modifier.height(24.dp))
            MenuLink(text = "Photo", onClick = onTakePhotoClick)

            Spacer(modifier = Modifier.height(32.dp))
            MenuLink(text = "More", onClick = onTakePhotoClick, fontSize = 18)
            Spacer(modifier = Modifier.height(12.dp))
            MenuLink(text = "Settings", onClick = onTakePhotoClick, fontSize = 18)
        }

        // Down arrow at the bottom
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

@Preview(showBackground = true)
@Composable
fun MainMenuScreenPreview() {
    MaterialTheme {
        MainMenuScreen()
    }
}