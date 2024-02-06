package me.tbandawa.android.online.gallery.demo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GalleryScreen(
    navController: NavController,
    galleryId: Long,
    showDelete: Boolean,
    galleryState: ResourceState<Gallery>,
    deleteState: ResourceState<Boolean>,
    getGallery: (galleryId: Long) -> Unit,
    deleteGallery: (galleryId: Long) -> Unit,
    resetState: () -> Unit
) {

    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    var galleryTitle by remember { mutableStateOf("") }
    var galleryDate by remember { mutableStateOf("") }
    var photoUrl by remember { mutableStateOf("") }
    var isDeleting by remember { mutableStateOf(false) }
    var isDeleted by remember { mutableStateOf(false) }
    var isDeletable by remember { mutableStateOf(showDelete) }

    var errorTitle by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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

    when (deleteState) {
        is ResourceState.Loading -> {
            isDeleting = true
            isDeleted = false
        }
        is ResourceState.Success -> {
            isDeleting = false
            isDeleted = true
            galleryTitle = ""
            galleryDate = ""
            isDeletable = false
            showBottomSheet = false
        }
        is ResourceState.Error -> {
            isDeleting = false
            isDeleted = false
        }
        is ResourceState.Empty -> {
            isDeleting = false
            isDeleted = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Scaffold(
            topBar = {
                GalleryToolbar(
                    title = galleryTitle,
                    time = galleryDate,
                    navController = navController,
                    showDelete = isDeletable,
                    deleteGallery = {
                        showBottomSheet = !showBottomSheet
                    }
                )
            },
            containerColor = Color.White
        ) {

            when(galleryState) {
                is ResourceState.Loading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Loading Results...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0x90024040)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator(color = Color(0x90024040))
                    }
                }
                is ResourceState.Success -> {
                    if (isDeleted) { // Show deleted message
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.img_delete),
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .size(85.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Gallery Deleted",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0x90024040)
                            )
                        }
                    } else { // Show Gallery
                        val gallery = galleryState.data
                        galleryTitle = gallery.title
                        galleryDate = convertDate(YYYY_MM_DD_T, MMM_DD_YYYY, gallery.created)
                        if (photoUrl.isBlank()) {
                            photoUrl = gallery.images[0].image
                        }
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                                .padding(start = 16.dp, end = 16.dp)
                        ) {

                            val (image, info) = createRefs()

                            AsyncImage(
                                model = photoUrl,
                                contentDescription = "Image",
                                modifier = Modifier
                                    .constrainAs(image) {
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(info.top)
                                        height = Dimension.fillToConstraints
                                    }
                                    .clip(RoundedCornerShape(5.dp))
                            )

                            Column(
                                modifier = Modifier
                                    .constrainAs(info) {
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        bottom.linkTo(parent.bottom)
                                    }
                                    .wrapContentHeight()
                                    .padding(top = 5.dp)
                            ) {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier
                                        .padding(top = 10.dp, bottom = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    items(gallery.images) { image ->
                                        AsyncImage(
                                            model = image.thumbnail,
                                            contentDescription = "Image",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .padding(top = 5.dp)
                                                .height(75.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .clickable {
                                                    photoUrl = image.image
                                                }
                                        )
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(top = 5.dp, bottom = 10.dp)
                                ) {
                                    Card(
                                        shape = RoundedCornerShape(5.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White
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
                    }
                }
                is ResourceState.Error -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.img_error),
                            tint = Color(0x90f55050),
                            contentDescription = "Error",
                            modifier = Modifier
                                .size(85.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Something went wrong",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0x90f55050)
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

            // Show delete confirmation dialog
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                    containerColor = Color.White,
                    dragHandle = null,
                    shape = RoundedCornerShape(topStartPercent = 5, topEndPercent = 5)
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(5.dp))
                            .background(
                                MaterialTheme.colorScheme.surface,
                            )
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(top = 10.dp, bottom = 5.dp),
                                textAlign = TextAlign.Center,
                                text = "Gallery Delete",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            )
                            Text(
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(top = 5.dp, bottom = 25.dp),
                                textAlign = TextAlign.Center,
                                text = "Do you want to delete this gallery?",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontSize = 14.sp
                                )
                            )
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                showBottomSheet = false
                                            }
                                        }
                                    },
                                    shape = RoundedCornerShape(20),
                                    modifier = Modifier
                                        .height(35.dp)
                                        .weight(0.45f),
                                    enabled = !isDeleting
                                ) {
                                    Text(
                                        text = "Cancel",
                                        style = TextStyle(
                                            color = Color(0xff024040),
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                                Spacer(Modifier.weight(0.1f))
                                Button(
                                    onClick = {
                                        deleteGallery(galleryId)
                                    },
                                    shape = RoundedCornerShape(20),
                                    modifier = Modifier
                                        .height(35.dp)
                                        .weight(0.45f),
                                    enabled = !isDeleting,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xff45f54242)
                                    )
                                ) {
                                    Text(
                                        text = if (isDeleting) "Deleting..." else "Delete",
                                        style = TextStyle(
                                            color = Color.Red,
                                            fontSize = 14.sp
                                        )
                                    )
                                }
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(55.dp)
                            )
                        }
                    }
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
        galleryId = 0,
        showDelete = true,
        galleryState = ResourceState.Empty,
        deleteState = ResourceState.Success(true),
        getGallery = { },
        deleteGallery = { },
        resetState = { }
    )
}
