package me.tbandawa.android.gallerydemo.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.R
import me.tbandawa.android.gallerydemo.ui.components.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsScreen(
    navController: NavController
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        val scrollState = rememberScrollState()
        val notificationCheckedState = remember{ mutableStateOf(false)}

        Scaffold(
            topBar = { NavigationToolbar(title = "Settings", navController) }
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                val (settingsLayout, logoutButton) = createRefs()

                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 5.dp)
                        .constrainAs(settingsLayout) {
                            top.linkTo(parent.top)
                        }
                ) {

                    // Account setting
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_profile),
                                contentDescription = "account",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            Text(
                                text = "Account",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color(0xff024040)))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp)
                            .height(35.dp)
                            .clickable {
                                navController.navigate(Screen.EditProfile.route)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Edit Profile",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .weight(1f)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_right_arrow),
                            contentDescription = "account",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(16.dp)
                        )

                    }

                    // Notification setting
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_notification),
                                contentDescription = "notification",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            Text(
                                text = "Notifications",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color(0xff024040)))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp)
                            .height(35.dp)
                            .clickable {

                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Notifications",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .weight(1f)
                        )
                        Switch(
                            checked = notificationCheckedState.value,
                            onCheckedChange = {
                                notificationCheckedState.value = it
                            }
                        )
                    }

                    // Theme setting
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_theme),
                                contentDescription = "theme",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            Text(
                                text = "Theme",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color(0xff024040)))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp)
                            .height(35.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Theme",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .weight(1f)
                        )
                        Surface(
                            color = Color.LightGray,
                            contentColor = Color.LightGray,
                            shape = CircleShape,
                            modifier = Modifier
                                .padding(1.dp)
                                .height(height = 30.dp)
                        ) {
                            Row {
                                UnitChip(preferences = GalleryPreference(true, true), "Light"){}
                                UnitChip(preferences = GalleryPreference(true, true), "Dark"){}
                            }
                        }
                    }

                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                        .constrainAs(logoutButton) {
                            bottom.linkTo(parent.bottom)
                        },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff024040)
                    )
                ){
                    Image(
                        painterResource(id = R.drawable.ic_logout),
                        contentDescription ="Edit",
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(20.dp)
                    )
                    Text(
                        text = "Log Out",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}
