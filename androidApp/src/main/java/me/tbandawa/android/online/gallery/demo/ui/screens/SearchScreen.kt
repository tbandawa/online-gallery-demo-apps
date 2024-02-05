package me.tbandawa.android.online.gallery.demo.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.items
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.demo.ui.components.GalleryItem
import me.tbandawa.android.online.gallery.demo.ui.components.NavigationToolbar
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavController,
    navigateToGallery: (galleryId: Long, galleryUserId: Long, userId: Long) -> Unit,
    searchGalleries: (query: String) -> Unit,
    galleriesState: ResourceState<List<Profile>>
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        var textSearch by remember { mutableStateOf(TextFieldValue("")) }
        var galleries = remember { listOf<Gallery>() }

        var isLoading by remember { mutableStateOf(false) }
        var isSuccess by remember { mutableStateOf(false) }
        var isError by remember { mutableStateOf(false) }

        when (galleriesState) {
            is ResourceState.Loading -> {
                isLoading = true
                isError = false
            }
            is ResourceState.Success -> {
                isLoading = false
                isSuccess = true
            }
            is ResourceState.Error -> {
                isLoading = false
                isError = true
            }
            is ResourceState.Empty -> {
                isLoading = false
                isSuccess = false
                isError = false
            }
        }

        Scaffold(
            topBar = {
                NavigationToolbar(
                    "Search",
                    navController
                )
            },
            containerColor = Color.White
        ) { it ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                Column {

                    BasicTextField(
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            color = Color(0xff024040)
                        ),
                        value = textSearch,
                        singleLine = true,
                        enabled = true,
                        onValueChange = { input ->
                            textSearch = input
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        decorationBox = { innerTextField ->
                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color(0xffF0F5F1),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color.Transparent,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 10.dp, vertical = 7.dp)
                            ) {
                                val (icon, text) = createRefs()
                                Box(
                                    modifier = Modifier
                                        .constrainAs(icon) {
                                            start.linkTo(parent.start)
                                            top.linkTo(parent.top)
                                            end.linkTo(text.start)
                                            bottom.linkTo(parent.bottom)
                                        }
                                        .size(25.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "searchIcon",
                                        tint = Color(0xff024040)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .constrainAs(text) {
                                            start.linkTo(icon.end)
                                            top.linkTo(parent.top)
                                            end.linkTo(parent.end)
                                            bottom.linkTo(parent.bottom)
                                            width = Dimension.fillToConstraints
                                        }
                                ) {
                                    if (textSearch.text.isEmpty()) {
                                        Text(
                                            text = "Type to search...",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Normal,
                                            color = Color(0x90024040)
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (isSuccess) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(it)
                                    .padding(start = 16.dp, top = 0.dp, end = 16.dp)
                            ) {
                                items(galleries) { gallery ->
                                    GalleryItem(
                                        gallery = gallery,
                                        navigateToGallery = { galleryId ->
                                            navigateToGallery(galleryId, 0, 1)
                                        }
                                    )
                                    Spacer(
                                        modifier = Modifier
                                            .height(15.dp)
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

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        navController = rememberNavController(),
        navigateToGallery = { _, _, _ -> },
        searchGalleries = { _ -> },
        galleriesState = ResourceState.Empty
    )
}
