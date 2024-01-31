package me.tbandawa.android.online.gallery.demo.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.imageLoader
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.data.viewmodel.ProfileViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.ErrorDialog
import me.tbandawa.android.online.gallery.demo.ui.components.NavigationToolbar
import me.tbandawa.android.online.gallery.demo.ui.components.SuccessDialog
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun EditProfilePhotoScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val profileViewModel: ProfileViewModel = koinViewModel()
    val profilePhotoState by profileViewModel.profilePhotoResource.collectAsState()

    var isLoading by remember { mutableStateOf(false) }

    var photoUrl by remember { mutableStateOf("") }
    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                photoUri = it
            }
        }
    )

    LaunchedEffect(Unit) {
        scope.launch {
            val user = profileViewModel.getUserData()!!
            photoUrl = user.profilePhoto.image!!
        }
    }

    when(profilePhotoState) {
        is ResourceState.Loading -> {
            isLoading = true
        }
        is ResourceState.Success -> {
            val profilePhoto = (profilePhotoState as ResourceState.Success).data
            photoUrl = profilePhoto.image!!
            isLoading = false
            photoUri = null
            SuccessDialog(message = "Changes Successfully Saved") {
                profileViewModel.resetState()
            }
        }
        is ResourceState.Error -> {
            val error = (profilePhotoState as ResourceState.Error<*>).data!!
            isLoading = false
            var errorMessage = ""
            for(index in error.messages!!.indices) {
                val newLine = if (index == (error.messages!!.size - 1)) "" else "\n"
                errorMessage += "${error.messages!![index]}$newLine"
            }
            ErrorDialog(message = errorMessage) {
                profileViewModel.resetState()
            }
        }
        is ResourceState.Empty -> {
            isLoading = false
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            topBar = {
                NavigationToolbar(
                    "Edit Profile Photo", navController
                )
            },
            containerColor = Color.White
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(start = 16.dp, top = 0.dp, end = 16.dp)
            ) {

                val (photoLayout, controlLayout) = createRefs()

                AsyncImage(
                    model = if (photoUri != null) photoUri else photoUrl,
                    contentDescription = "Profile Photo",
                    modifier = Modifier
                        .constrainAs(photoLayout) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                )

                Row (
                    modifier = Modifier
                        .constrainAs(controlLayout) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = {
                            galleryLauncher.launch("image/*")
                        },
                        modifier = Modifier
                            .height(35.dp),
                        enabled = !isLoading
                    ) {
                        Text(
                            text = "Select Image",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontSize = 14.sp
                            )
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    if (photoUri != null) {
                        Button(
                            onClick = {
                                context.imageLoader.diskCache?.clear()
                                context.imageLoader.memoryCache?.clear()
                                profileViewModel.uploadProfilePicture(
                                    photoTitle = getFileNameFromUri(context, photoUri!!)!!,
                                    photoBytes = getBytesFromUri(context, photoUri)!!
                                )
                            },
                            shape = RoundedCornerShape(50),
                            modifier = Modifier
                                .padding(top = 0.dp, bottom = 0.dp)
                                .height(35.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xff024040)
                            )
                        ){
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(21.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp)
                            } else {
                                Text(
                                    text = "Update",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp
                                    )
                                )
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
fun EditProfilePhotoScreenPreview() {
    EditProfilePhotoScreen(navController = rememberNavController())
}