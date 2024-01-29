package me.tbandawa.android.online.gallery.demo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropScaffoldDefaults
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.Error
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.data.viewmodel.ProfileViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.ProfileGalleries
import me.tbandawa.android.online.gallery.demo.ui.components.MainToolbar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val scope = rememberCoroutineScope()
        val profileViewModel: ProfileViewModel = koinViewModel()
        val profileState by profileViewModel.profileResource.collectAsState()

        var isLoading by remember { mutableStateOf(false) }
        var isSuccess by remember { mutableStateOf(false) }
        var isError by remember { mutableStateOf(false) }

        val photoUrl = remember { mutableStateOf("") }
        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val galleryCount = remember { mutableIntStateOf(0) }
        var galleryList = remember { listOf<Gallery>() }

        LaunchedEffect(Unit) {
            scope.launch {
                val user = profileViewModel.getUserData()!!
                firstName.value = user.firstname
                lastName.value = user.lastname
                email.value = user.email
                photoUrl.value = user.profilePhoto.thumbnail!!
                profileViewModel.getProfile()
            }
        }

        val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

        when (profileState) {
            is ResourceState.Loading -> {
                isLoading = true
                isError = false
            }
            is ResourceState.Success -> {
                val profile = (profileState as ResourceState.Success).data
                galleryCount.intValue = profile.gallery.size
                galleryList = profile.gallery
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

        BackdropScaffold(
            scaffoldState = scaffoldState,
            peekHeight = BackdropScaffoldDefaults.PeekHeight,
            persistentAppBar = true,
            stickyFrontLayer = true,
            headerHeight = BackdropScaffoldDefaults.HeaderHeight,
            frontLayerShape = BackdropScaffoldDefaults.frontLayerShape,
            frontLayerElevation = 10.dp,
            frontLayerScrimColor = Color.Unspecified,
            frontLayerBackgroundColor = Color.White,
            backLayerBackgroundColor = Color.White,
            appBar =  {
                MainToolbar("Profile")
            },
            backLayerContent = {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 5.dp)
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    AsyncImage(
                        model = photoUrl.value,
                        contentDescription = "Profile Photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(145.dp)
                            .clip(CircleShape)
                            .align(alignment = CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "${firstName.value} ${lastName.value}",
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .padding(start = 0.dp, top = 10.dp)
                            .align(alignment = CenterHorizontally)
                    )

                    Text(
                        text = email.value,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier
                            .align(alignment = CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            horizontalAlignment = CenterHorizontally
                        ) {
                            Card(
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${galleryCount.intValue}",
                                        style = TextStyle(
                                            color = Color(0xff024040),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .padding(start = 10.dp)
                                    )
                                    Text(
                                        text = "Posts",
                                        style = TextStyle(
                                            color = Color(0xff024040),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 14.sp
                                        ),
                                        modifier = Modifier
                                            .padding(start = 5.dp, end = 10.dp)
                                    )

                                    Box(
                                        modifier = Modifier
                                            .background(color = Color(0xff2596be))
                                            .clickable {
                                                navController.navigate("profile/edit")
                                            }
                                            .padding(
                                                PaddingValues(
                                                    horizontal = 5.dp,
                                                    vertical = 5.dp
                                                )
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                Icons.Default.Edit,
                                                contentDescription = "Edit",
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .size(25.dp)
                                                    .padding(
                                                        PaddingValues(
                                                            horizontal = 5.dp,
                                                            vertical = 0.dp
                                                        )
                                                    )
                                            )
                                            Text(
                                                text = "Edit",
                                                fontSize = 14.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Normal,
                                                modifier = Modifier
                                                    .padding(end = 5.dp)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .background(color = Color(0xffFf7878))
                                            .clip(
                                                RoundedCornerShape(
                                                    topEnd = 30.dp,
                                                    bottomEnd = 30.dp
                                                )
                                            )
                                            .clickable {

                                            }
                                            .padding(
                                                PaddingValues(
                                                    horizontal = 5.dp,
                                                    vertical = 5.dp
                                                )
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                Icons.Default.ExitToApp,
                                                contentDescription = "Logout",
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .size(25.dp)
                                                    .padding(
                                                        PaddingValues(
                                                            horizontal = 5.dp,
                                                            vertical = 0.dp
                                                        )
                                                    )
                                            )
                                            Text(
                                                text = "Logout",
                                                fontSize = 14.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Normal,
                                                modifier = Modifier
                                                    .padding(end = 5.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                }
            },
            frontLayerContent = {
                Box(
                    contentAlignment = TopCenter,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (isLoading) {
                        Column {
                            Spacer(modifier = Modifier.height(90.dp))
                            Text(
                                text = "Loading",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                    if (isSuccess) {
                        ProfileGalleries(galleries = galleryList)
                    }
                    if (galleryList.isEmpty() && !isLoading) {
                        Column {
                            Spacer(modifier = Modifier.height(90.dp))
                            Text(
                                text = "No Galleries",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                    if (isError) {
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
                                    profileViewModel.getProfile()
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
            }
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}