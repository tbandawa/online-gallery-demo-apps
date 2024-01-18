package me.tbandawa.android.online.gallery.demo.ui.screens

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import me.tbandawa.android.online.gallery.R
import me.tbandawa.android.online.gallery.demo.ui.components.MainToolbar
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreateScreen(

) {

    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var textTitle by remember { mutableStateOf(TextFieldValue("")) }
    var textDescription by remember { mutableStateOf(TextFieldValue("")) }

    var isTitleValid by remember { mutableStateOf(true) }
    var isDescriptionValid by remember { mutableStateOf(true) }

    var selectImages by remember { mutableStateOf(listOf<Uri>()) }
    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
        selectImages = uriList
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            topBar = {
                MainToolbar(
                    "New Gallery"
                )
            },
            containerColor = Color.White
        ) { it ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Column {

                        TextField(
                            value = textTitle,
                            singleLine = true,
                            enabled = !isLoading,
                            onValueChange = { input ->
                                textTitle = input
                                isTitleValid = input.text.isNotBlank()
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
                            value = textDescription,
                            singleLine = false,
                            maxLines = 4,
                            minLines = 4,
                            enabled = !isLoading,
                            onValueChange = { input ->
                                textDescription = input
                                isDescriptionValid = input.text.isNotBlank()
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
                                )
                            ,
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

                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = "Images",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            selectImages.forEach { uri ->
                                ImageFile(uri = uri)
                            }
                            Box(
                                modifier = Modifier
                                    .padding(top = 0.dp)
                            ) {
                                FilledTonalButton(
                                    onClick = {
                                        galleryLauncher.launch("image/*")
                                    },
                                    shape = RoundedCornerShape(25)
                                ) {
                                    Image(
                                        painterResource(id = R.drawable.ic_add),
                                        contentDescription ="Add Image",
                                        modifier = Modifier.size(20.dp))
                                    Text(
                                        text = "Add to cart",
                                        Modifier
                                            .padding(start = 10.dp)
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

@Composable
fun ImageFile(uri: Uri) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(color = Color(0xfff0f0f0))
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(uri),
            contentDescription = "Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(5.dp))
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 15.dp)
        ) {

            val (info, closeIcon) = createRefs()

            Column(
                modifier = Modifier
                    .constrainAs(info) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            ) {
                Text(
                    text = "Some text",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = "Some text",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(closeIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .size(20.dp)
                    .clickable(
                        enabled = true,
                        onClick = { }
                    )
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .padding(top = 5.dp, end = 8.dp)
                .clickable(
                    enabled = true,
                    onClick = {

                    }
                )
        )
    }
}

@Preview
@Composable
fun CreateScreenPreview() {
    CreateScreen()
}