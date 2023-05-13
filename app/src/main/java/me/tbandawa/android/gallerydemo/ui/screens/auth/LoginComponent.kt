package me.tbandawa.android.gallerydemo.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.ui.screens.gallery.GalleryScreen

@Composable
fun LoginComponent() {
    Surface(modifier = Modifier
            .padding(top = 25.dp, bottom = 25.dp, start = 25.dp, end = 25.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = "Share your \ndiscoveries with the \nworld",
                style = TextStyle(
                    color = Color(0xff024040),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                ),
                modifier = Modifier.padding(start = 2.dp, end = 2.dp)
            )

        }
    }
}

@Preview
@Composable
fun LoginComponentPreview() {
    LoginComponent()
}