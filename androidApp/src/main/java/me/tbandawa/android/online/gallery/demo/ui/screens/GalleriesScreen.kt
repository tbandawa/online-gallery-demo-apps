package me.tbandawa.android.online.gallery.demo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.demo.ui.components.MainToolbar

@Composable
fun GalleriesScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Scaffold(
            topBar = {
                MainToolbar(
                    "Galleries"
                )
            },
            containerColor = Color.White
        ) { it

        }
    }
}

@Preview
@Composable
fun GalleriesScreenPreview() {
    GalleriesScreen()
}
