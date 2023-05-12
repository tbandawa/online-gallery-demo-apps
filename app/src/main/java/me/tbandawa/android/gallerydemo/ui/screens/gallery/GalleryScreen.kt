package me.tbandawa.android.gallerydemo.ui.screens.gallery

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.R
import me.tbandawa.android.gallerydemo.ui.components.GalleryItem
import me.tbandawa.android.gallerydemo.ui.components.GalleryToolBar
import me.tbandawa.android.gallerydemo.ui.components.MessageBox
import me.tbandawa.android.gallerydemo.ui.components.Screen

@Composable
fun GalleryScreen(
    navController: NavController
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scrollState = rememberScrollState()

        Scaffold(
            topBar = { GalleryToolBar(navController) }
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(start = 16.dp, top = 5.dp, end = 16.dp, bottom = 5.dp)
            ) {

                var searchText by remember { mutableStateOf(TextFieldValue("")) }

                Image(
                    painter = painterResource(R.drawable.ic_avatar),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(85.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                        .clickable {
                            navController.navigate(Screen.Profile.route)
                        }
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Share your \ndiscoveries with the \nworld",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    ),
                    modifier = Modifier.padding(start = 2.dp, end = 2.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))
                TextField(
                    value = searchText,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "searchIcon",
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                    },
                    onValueChange = {
                        searchText = it
                    },
                    placeholder = { Text(text = "Search Galleries") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color(0xffF0F5F1),
                            shape = RoundedCornerShape(60.dp)
                        ),
                    shape = RoundedCornerShape(60.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color(0xff024040),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        placeholderColor = Color(0x90024040),
                        leadingIconColor = Color(0xff024040)
                    )
                )

                MessageBox(
                    title = "Image Gallery",
                    message = "Upload images to gallery and let others view them"
                )

                val items = listOf("Gallery Item 1", "Gallery Item 2", "Gallery Item 3", "Gallery Item 4", "Gallery Item 5")

                Spacer(modifier = Modifier.height(20.dp))
                items.forEach { item ->
                    GalleryItem(
                        painter = painterResource(id = R.drawable.free_1),
                        title = "Cyberpunk is Not Dead",
                        description = "Cyber-chic is trending, and we’re plugged in to the possibilities. Add some futuristic flair and dystopian vibes to your upcoming projects with this sci-fi inspired collection.",
                        user = "Tendai Bandawa",
                        date = "5 Nov 2022",
                        count = 1
                    )
                }

            }
        }

    }
}

@Preview
@Composable
fun GalleryScreenPreview() {
    GalleryScreen(navController = rememberNavController())
}