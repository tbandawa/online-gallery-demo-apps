package me.tbandawa.android.online.gallery.demo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.R
import me.tbandawa.android.online.gallery.data.domain.models.Error
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.demo.ui.components.GalleryToolbar
import me.tbandawa.android.online.gallery.demo.utils.MMM_DD_YYYY
import me.tbandawa.android.online.gallery.demo.utils.YYYY_MM_DD_T
import me.tbandawa.android.online.gallery.demo.utils.convertDate
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GalleryScreen(
    navController: NavController,
    galleryId: Long,
    galleryState: ResourceState<Gallery>,
    getGallery: (galleryId: Long) -> Unit,
    resetState: () -> Unit
) {

    val scope = rememberCoroutineScope()

    val galleryTitle = remember { mutableStateOf("") }
    val galleryDate = remember { mutableStateOf("") }
    val photoUrl = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        scope.launch {
            getGallery(galleryId)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            resetState()
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
                        if (photoUrl.value.isBlank()) {
                            photoUrl.value = gallery.images[0].image
                        }

                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            val (image, row, info) = createRefs()
                            AsyncImage(
                                model = photoUrl.value,
                                contentDescription = "Image",
                                modifier = Modifier
                                    .constrainAs(image) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                        end.linkTo(parent.end)
                                    }
                                    .fillMaxWidth()
                                    .fillMaxHeight(.75f)
                                    .clip(RoundedCornerShape(5.dp))
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier
                                    .constrainAs(row) {
                                        start.linkTo(parent.start)
                                        top.linkTo(image.bottom)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(info.top)
                                    }
                                    .padding(top = 10.dp, bottom = 10.dp)
                                    .fillMaxWidth()
                            ) {
                                items(gallery.images) { image ->
                                    AsyncImage(
                                        model = image.thumbnail,
                                        contentDescription = "Image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                            .height(100.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .clickable {
                                                photoUrl.value = image.image
                                            }
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .constrainAs(info) {
                                        start.linkTo(parent.start)
                                        top.linkTo(row.bottom)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(parent.bottom)
                                    }
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(top = 5.dp, bottom = 10.dp)
                            ) {
                                Card(
                                    shape = RoundedCornerShape(5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xffdff2d5)
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                ) {
                                    Column {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 5.dp, top = 5.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            AsyncImage(
                                                model = gallery.profile?.profilePhoto?.thumbnail,
                                                placeholder = painterResource(R.drawable.ic_user),
                                                contentDescription = "Profile Photo",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(25.dp)
                                                    .clip(CircleShape)
                                            )
                                            Column {
                                                Text(
                                                    text = "${gallery.profile?.firstname} ${gallery.profile?.lastname}",
                                                    style = TextStyle(
                                                        color = Color(0xff024040),
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 12.sp
                                                    ),
                                                    modifier = Modifier
                                                        .padding(start = 5.dp, top = 0.dp)
                                                )
                                                Text(
                                                    text = "${gallery.images.size} photos",
                                                    style = TextStyle(
                                                        color = Color(0xff024040),
                                                        fontWeight = FontWeight.Medium,
                                                        fontSize = 12.sp
                                                    ),
                                                    modifier = Modifier
                                                        .padding(start = 5.dp, top = 0.dp)
                                                )
                                            }
                                        }
                                    }
                                    Text(
                                        text = gallery.description,
                                        style = TextStyle(
                                            color = Color(0xff024040),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 12.sp
                                        ),
                                        modifier = Modifier
                                            .padding(start = 35.dp, bottom = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                    is ResourceState.Error -> {
                        val error = (galleryState as ResourceState.Error<*>).data!!

                        var errorMessage = ""
                        for(message in error.messages!!) {
                            errorMessage += "$message\n"
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = errorMessage
                            )
                            TextButton(
                                onClick = {
                                    getGallery(galleryId)
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
                    is ResourceState.Empty -> { }
                }
            }
        }
    }
}

@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryScreen(
        navController = rememberNavController(),
        galleryId = 1,
        galleryState = ResourceState.Error(Error("timeStamp", 400, "Error", arrayListOf("An Error Occurred"))),
        getGallery = {},
        resetState = {}
    )
}
