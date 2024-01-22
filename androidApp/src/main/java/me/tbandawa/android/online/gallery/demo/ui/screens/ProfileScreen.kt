package me.tbandawa.android.online.gallery.demo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.data.viewmodel.ProfileViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.Galleries
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

        LaunchedEffect(Unit) {
            scope.launch {
                profileViewModel.getProfile()
            }
        }

        val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

        when (profileState) {
            is ResourceState.Empty -> {}
            is ResourceState.Loading -> {
                Text(
                    text = "Loading..."
                )
            }
            is ResourceState.Success -> {

                val profile = (profileState as ResourceState.Success).data

                val profilePhoto = rememberImagePainter(
                    data = profile.profilePhoto.thumbnail,
                    builder = {
                        crossfade(true)
                    }
                )

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
                                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 5.dp)
                                .wrapContentHeight()
                                .fillMaxWidth()
                        ) {

                            Image(
                                painter = profilePhoto,
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(145.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape)
                                    .align(alignment = CenterHorizontally)
                            )

                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "${profile.firstname} ${profile.lastname}",
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
                                text = profile.email,
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
                                                text = "${profile.gallery.size}",
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
                                                            .padding(PaddingValues(horizontal = 5.dp, vertical = 0.dp))
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
                                                            .padding(PaddingValues(horizontal = 5.dp, vertical = 0.dp))
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
                        Galleries(galleries = profile.gallery)
                    }
                )
            }
            is ResourceState.Error -> {
                Text(
                    text = "Error!"
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}