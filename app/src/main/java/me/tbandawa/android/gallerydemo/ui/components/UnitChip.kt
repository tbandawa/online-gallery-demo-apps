package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@Composable
fun UnitChip(
    preferences: GalleryPreference,
    text: String,
    setPreference: (GalleryPreference) -> Unit
) {

    Surface(
        color = when (text) {
            "Light" -> {
                if (preferences.lightTheme)
                    Color(0xff024040)
                else
                    Color.Transparent
            }
            "Dark" -> {
                if (!preferences.lightTheme)
                    Color(0xff024040)
                else
                    Color.Transparent
            }
            else -> Color.Transparent
        },
        contentColor = Color.White,
        shape = CircleShape,
        modifier = Modifier.padding(3.dp),
        onClick = {

        }
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}

data class GalleryPreference(
    var notification: Boolean,
    var lightTheme: Boolean
)

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun UnitChipPreview() {
    UnitChip(preferences = GalleryPreference(true, true), "Light"){}
}