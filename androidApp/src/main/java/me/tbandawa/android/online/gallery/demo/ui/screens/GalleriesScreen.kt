package me.tbandawa.android.online.gallery.demo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.flowOf
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.demo.ui.components.GalleryItem
import me.tbandawa.android.online.gallery.demo.ui.components.HomeToolBar

@Composable
fun GalleriesScreen(
    navController: NavController,
    galleries: LazyPagingItems<Gallery>,
    retry: () -> Unit,
    navigateToGallery: (galleryId: Long) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            topBar = {
                HomeToolBar(
                    title = "Galleries",
                    navController = navController
                )
            },
            containerColor = Color.White
        ) {

            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(start = 16.dp, top = 0.dp, end = 16.dp)
            ) {

                items(galleries) { gallery ->
                    GalleryItem(
                        gallery = gallery!!,
                        navigateToGallery = navigateToGallery
                    )
                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                    )
                }

                when (galleries.loadState.refresh) {
                    is LoadState.Error -> { }
                    is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = "Loading Galleries..."
                                )
                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> {}
                }

                when (galleries.loadState.append) {
                    is LoadState.Error -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = "Error"
                                )
                                TextButton(
                                    onClick = {
                                        retry()
                                    },
                                    modifier = Modifier
                                        .height(35.dp)
                                ) {
                                    Text(
                                        text = "Retry",
                                        style = TextStyle(
                                            color = Color(0xff024040),
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                    is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Loading...")
                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}

@Preview
@Composable
fun GalleriesScreenPreview() {
    GalleriesScreen(
        navController = rememberNavController(),
        galleries = flowOf(PagingData.from(emptyList<Gallery>())).collectAsLazyPagingItems(),
        retry = {},
        navigateToGallery = {}
    )
}
