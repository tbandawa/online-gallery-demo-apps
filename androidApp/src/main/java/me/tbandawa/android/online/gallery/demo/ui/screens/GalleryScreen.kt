package me.tbandawa.android.online.gallery.demo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.data.viewmodel.GalleryViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.GalleryToolbar
import me.tbandawa.android.online.gallery.demo.utils.MMM_DD_YYYY
import me.tbandawa.android.online.gallery.demo.utils.YYYY_MM_DD_T
import me.tbandawa.android.online.gallery.demo.utils.convertDate
import org.koin.androidx.compose.koinViewModel
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GalleryScreen(
    navController: NavController,
    galleryId: Long
) {

    val scope = rememberCoroutineScope()
    val galleryViewModel: GalleryViewModel = koinViewModel()
    val galleryState by galleryViewModel.galleryResource.collectAsState()

    val galleryTitle = remember { mutableStateOf("") }
    val galleryDate = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        scope.launch {
            galleryViewModel.getGallery(galleryId)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            galleryViewModel.resetState()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            topBar = {
                GalleryToolbar(
                    title = galleryTitle.value,
                    time = galleryDate.value,
                    navController = navController
                )
            },
            containerColor = Color.White
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(start = 16.dp, top = 0.dp, end = 16.dp)
            ) {

                when(galleryState) {
                    is ResourceState.Loading -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Loading..."
                            )
                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                    is ResourceState.Success -> {
                        val gallery = (galleryState as ResourceState.Success<Gallery>).data
                        galleryTitle.value = gallery.title
                        galleryDate.value = convertDate(YYYY_MM_DD_T, MMM_DD_YYYY, gallery.created)

                    }
                    is ResourceState.Error -> {
                        val error = (galleryState as ResourceState.Error<*>).data!!

                    }
                    is ResourceState.Empty -> { }
                }
            }
        }
    }
}

@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryScreen(navController = rememberNavController(), galleryId = 1)
}
