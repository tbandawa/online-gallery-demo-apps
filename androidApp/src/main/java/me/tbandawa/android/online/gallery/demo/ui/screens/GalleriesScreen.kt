package me.tbandawa.android.online.gallery.demo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.demo.ui.components.HomeToolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleriesScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            topBar = {
                HomeToolBar(
                    "Galleries",
                    navController,
                    scrollBehavior
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
    GalleriesScreen(navController = rememberNavController())
}
