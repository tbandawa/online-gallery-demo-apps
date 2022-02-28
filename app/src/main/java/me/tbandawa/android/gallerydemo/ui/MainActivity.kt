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
import me.tbandawa.android.gallerydemo.ui.components.GalleryItem
import me.tbandawa.android.gallerydemo.ui.components.GalleryToolBar
import me.tbandawa.android.gallerydemo.ui.components.MessageBox
import me.tbandawa.android.gallerydemo.ui.theme.GalleryDemoTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Scaffold(
                        topBar = { GalleryToolBar(title = "Gallery"){} }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                        ) {
                            MessageBox(
                                title = "Image Gallery",
                                message = "Upload images to gallery and let others view them"
                            )

                            val items = listOf(
                                "Gallery Item 1", "Gallery Item 2", "Gallery Item 3", "Gallery Item 4", "Gallery Item 5",
                                "Gallery Item 6", "Gallery Item 7", "Gallery Item 8", "Gallery Item 9", "Gallery Item 10",
                                "Gallery Item 11", "Gallery Item 12", "Gallery Item 13", "Gallery Item 14", "Gallery Item 15"
                            )

                            LazyVerticalGrid(
                                cells = GridCells.Fixed(3),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.padding(top = 10.dp)
                            ) {
                                items(items.size) { itemIndex ->
                                    GalleryItem(
                                        painter = painterResource(id = R.drawable.free_1),
                                        title = items[itemIndex],
                                        count = itemIndex + 1
                                    )
                                }
                            }

                        }
                    }

                }
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
            GalleryToolBar(title = "Gallery") {}
        }
    }
}