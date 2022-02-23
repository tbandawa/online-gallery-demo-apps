package me.tbandawa.android.gallerydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import me.tbandawa.android.gallerydemo.ui.components.GalleryItem
import me.tbandawa.android.gallerydemo.ui.components.MessageBox
import me.tbandawa.android.gallerydemo.ui.theme.GalleryDemoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GalleryDemoTheme {
        Column {
            MessageBox(
                title = "Image Gallery",
                message = "Upload images to gallery and let others view them"
            )
            GalleryItem(
                painter = painterResource(id = R.drawable.free_1),
                title = "Gallery Title Goes Here",
                count = 10
            )
        }
    }
}