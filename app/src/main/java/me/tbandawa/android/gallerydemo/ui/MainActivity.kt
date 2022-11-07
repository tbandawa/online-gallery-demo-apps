package me.tbandawa.android.gallerydemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.tbandawa.android.gallerydemo.R
import me.tbandawa.android.gallerydemo.ui.components.GalleryItem
import me.tbandawa.android.gallerydemo.ui.components.GalleryToolBar
import me.tbandawa.android.gallerydemo.ui.components.MessageBox
import me.tbandawa.android.gallerydemo.ui.screens.GalleryScreen
import me.tbandawa.android.gallerydemo.ui.theme.GalleryDemoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryDemoTheme {
                GalleryScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GalleryDemoTheme {
        Column {
            GalleryToolBar {}
        }
    }
}