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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.data.viewmodel.ProfileViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.NavigationToolbar
import me.tbandawa.android.online.gallery.demo.ui.components.SuccessDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun EditProfilePhotoScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val profileViewModel: ProfileViewModel = koinViewModel()
    val profilePhotoState by profileViewModel.profilePhotoResource.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

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
            photoUrl = user.profilePhoto.thumbnail!!
        }
    }

    when(profilePhotoState) {
        is ResourceState.Loading -> {
            isLoading = true
            isError = false
        }
        is ResourceState.Success -> {
            //val profilePhoto = (profilePhotoState as ResourceState.Success).data
            //photoUri = null
            //photoUrl = profilePhoto.thumbnail!!
            isLoading = false
            isSuccess = true
        }
        is ResourceState.Error -> {
            val error = (profilePhotoState as ResourceState.Error<*>).data!!
            isLoading = false
            isError = true
        }
        is ResourceState.Empty -> {
            isLoading = false
            isSuccess = false
            isError = false
        }
    }

    SuccessDialog(showDialog = isSuccess, message = "Changes Successfully Saved") {
        val user = profileViewModel.getUserData()!!
        //photoUri = null
        //photoUrl = user.profilePhoto.thumbnail.toString()
        profileViewModel.resetState()
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
        ) { it ->

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {

                val (photoLayout, controlLayout) = createRefs()

                AsyncImage(
                    model = if (photoUri != null) photoUri else photoUrl,
                    contentDescription = "",
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
                        FilledTonalButton(
                            onClick = {
                                profileViewModel.uploadProfilePicture(
                                    photoTitle = getFileNameFromUri(context, photoUri!!)!!,
                                    photoBytes = getBytesFromUri(context, photoUri)!!
                                )
                            },
                            shape = RoundedCornerShape(5),
                            modifier = Modifier
                                .padding(top = 0.dp, bottom = 0.dp)
                                .height(35.dp),
                            enabled = !isLoading
                        ) {
                            Text(
                                text = if (isLoading) "Updating" else "Update",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 10.dp)
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
fun EditProfilePhotoScreenPreview() {
    EditProfilePhotoScreen(navController = rememberNavController())
}