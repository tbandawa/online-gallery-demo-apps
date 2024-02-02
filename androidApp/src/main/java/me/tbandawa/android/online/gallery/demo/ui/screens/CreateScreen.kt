package me.tbandawa.android.online.gallery.demo.ui.screens

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import me.tbandawa.android.online.gallery.R
import me.tbandawa.android.online.gallery.data.domain.models.Error
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.demo.ui.components.ErrorDialog
import me.tbandawa.android.online.gallery.demo.ui.components.MainToolbar
import me.tbandawa.android.online.gallery.demo.ui.components.SuccessDialog
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateScreen(
    galleryState: ResourceState<Gallery>,
    createGallery: (String, String, Map<String, ByteArray>) -> Unit,
    resetState: () -> Unit
) {

    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }
    val textTitle = rememberSaveable { mutableStateOf("") }
    val textDescription = rememberSaveable { mutableStateOf("") }

    var isTitleValid by remember { mutableStateOf(true) }
    var isDescriptionValid by remember { mutableStateOf(true) }

    var selectedUris by remember { mutableStateOf(listOf<Uri>()) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
        selectedUris += uriList
    }

    DisposableEffect(Unit) {
        onDispose {
            resetState()
        }
    }

    when(galleryState) {
        is ResourceState.Empty -> {
            isLoading = false
        }
        is ResourceState.Loading -> {
            isLoading = true
        }
        is ResourceState.Success -> {
            isLoading = false
            SuccessDialog(message = "Gallery Successfully Created") {
                textTitle.value = ""
                textDescription.value = ""
                selectedUris = emptyList()
                resetState()
            }
        }
        is ResourceState.Error -> {
            val error = (galleryState as ResourceState.Error<*>).data!!
            isLoading = false
            var errorMessage = ""
            for(index in error.messages!!.indices) {
                val newLine = if (index == (error.messages!!.size - 1)) "" else "\n"
                errorMessage += "${error.messages!![index]}$newLine"
            }
            ErrorDialog(message = errorMessage) {
               resetState()
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            topBar = {
                MainToolbar(
                    "New Gallery"
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

                val (info, grid, control) = createRefs()

                Column(
                    modifier = Modifier
                        .constrainAs(info) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                ) {
                    TextField(
                        value = textTitle.value,
                        singleLine = true,
                        enabled = !isLoading,
                        onValueChange = { input ->
                            textTitle.value = input
                            isTitleValid = input.isNotBlank()
                        },
                        placeholder = { Text(text = "Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = if (isTitleValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff024040),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedPlaceholderColor = if (isTitleValid) Color(0x90024040) else Color(0xfff55050),
                            disabledLeadingIconColor = Color(0xff024040)
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textDescription.value,
                        singleLine = false,
                        maxLines = 4,
                        minLines = 4,
                        enabled = !isLoading,
                        onValueChange = { input ->
                            textDescription.value = input
                            isDescriptionValid = input.isNotBlank()
                        },
                        placeholder = { Text(text = "Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = if (isDescriptionValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff024040),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedPlaceholderColor = if (isDescriptionValid) Color(0x90024040) else Color(0xfff55050),
                            disabledLeadingIconColor = Color(0xff024040)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Images",
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 0.dp)
                            .height(1.dp)
                            .background(color = Color(0xff024040))
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(grid) {
                            start.linkTo(parent.start)
                            top.linkTo(info.bottom)
                            end.linkTo(parent.end)
                            bottom.linkTo(control.top)
                            height = Dimension.fillToConstraints
                        }
                        .padding(bottom = 16.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        userScrollEnabled = true
                    ) {
                        items(selectedUris) { uri ->
                            ImageFile(
                                uri = uri
                            ) { selectedUri ->
                                val uriIndex = selectedUris.indexOf(selectedUri)
                                val flyUris = selectedUris.toMutableList()
                                flyUris.removeAt(uriIndex)
                                selectedUris = flyUris
                            }
                        }
                    }
                }

                Row (
                    modifier = Modifier
                        .constrainAs(control) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .padding(start = 16.dp, end = 16.dp),
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
                        Image(
                            painterResource(id = R.drawable.ic_add),
                            contentDescription ="Add Image",
                            modifier = Modifier.size(20.dp))
                        Text(
                            text = "Add Image",
                            Modifier
                                .padding(start = 10.dp),
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontSize = 14.sp
                            )
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Button(
                        onClick = {
                            isTitleValid = textTitle.value.isNotBlank()
                            isDescriptionValid = textDescription.value.isNotBlank()
                            if (isTitleValid && isDescriptionValid) {
                                val images = mutableMapOf<String, ByteArray>()
                                selectedUris.forEach { uri ->
                                    images[getFileNameFromUri(context, uri)!!] =
                                        getBytesFromUri(context, uri)!!
                                }
                                createGallery(
                                    textTitle.value,
                                    textDescription.value,
                                    images
                                )
                            }
                        },
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .height(35.dp),
                        enabled = selectedUris.isNotEmpty() || isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff024040)
                        )
                    ) {
                        Text(
                            text = if (isLoading) "Uploading..." else "Upload",
                            style = TextStyle(
                                color = if (selectedUris.isNotEmpty() || isLoading) Color.White else Color(0xff024040),
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageFile(
    uri: Uri,
    selectedUri: (uri: Uri) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 0.dp)
    ) {

        val (image, closeIcon) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(uri),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .size(115.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 5.dp, end = 5.dp)
                .constrainAs(closeIcon) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .size(20.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        selectedUri(uri)
                    }
                )
        )
    }
}

@SuppressLint("Range")
fun getFileNameFromUri(context: Context, uri: Uri): String? {
    val fileName: String?
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.moveToFirst()
    fileName = cursor?.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    cursor?.close()
    return fileName
}

fun getBytesFromUri(context: Context, uri: Uri?): ByteArray? {
    var data: ByteArray? = null
    try {
        val cr: ContentResolver = context.contentResolver
        val inputStream = cr.openInputStream(uri!!)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArrayOutput = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutput)
        data = byteArrayOutput.toByteArray()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
    return data
}

@Preview
@Composable
fun CreateScreenPreview() {
    CreateScreen(
        galleryState = ResourceState.Error(Error("timeStamp", 400, "Error", arrayListOf("An Error Occurred", "An Error Occurred"))),
        createGallery = { _, _, _ -> },
        resetState = {}
    )
}