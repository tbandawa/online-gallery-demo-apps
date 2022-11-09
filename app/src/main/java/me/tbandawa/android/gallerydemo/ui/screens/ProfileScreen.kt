package me.tbandawa.android.gallerydemo.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.android.gallerydemo.R
import me.tbandawa.android.gallerydemo.ui.components.ProfileToolBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

        BackdropScaffold(
            scaffoldState = scaffoldState,
            peekHeight = BackdropScaffoldDefaults.PeekHeight,
            persistentAppBar = true,
            stickyFrontLayer = true,
            headerHeight = BackdropScaffoldDefaults.HeaderHeight,
            frontLayerShape = BackdropScaffoldDefaults.frontLayerShape,
            frontLayerElevation = 10.dp,
            frontLayerScrimColor = Color.Unspecified,
            frontLayerBackgroundColor = Color(0xffCEE6E6),
            backLayerBackgroundColor = Color.White,
            appBar =  {
                ProfileToolBar{}
            },
            backLayerContent = {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 5.dp)
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(R.drawable.ic_avatar),
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
                        text = "Tendai Bandawa",
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        modifier = Modifier
                            .padding(start = 0.dp, top = 10.dp)
                            .align(alignment = CenterHorizontally)
                    )

                    Text(
                        text = "tonderaibandawa@gmail.com",
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier
                            .align(alignment = CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        modifier = Modifier
                            .height(35.dp)
                            .align(alignment = CenterHorizontally),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xff024040)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_edit),
                            contentDescription ="Edit",
                            modifier = Modifier
                                .padding(end = 5.dp)
                                .size(20.dp)
                        )
                        Text(
                            text = "Edit Profile",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                fontStyle = FontStyle.Italic,
                                fontSize = 14.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            horizontalAlignment = CenterHorizontally
                        ) {
                            Text(
                                text = "16",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                            )
                            Text(
                                text = "Posts",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = CenterHorizontally
                        ) {
                            Text(
                                text = "135",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                            )
                            Text(
                                text = "Views",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                }
            },
            frontLayerContent = {
                Column() {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .height(45.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(id = R.drawable.ic_camera),
                            contentDescription ="Camera",
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .size(25.dp)
                        )
                        Text(
                            text = "My Gallery",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}