package me.tbandawa.android.gallerydemo.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.R
import me.tbandawa.android.gallerydemo.ui.components.NavigationToolbar

@Composable
fun EditProfileScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Scaffold(
            topBar = { NavigationToolbar("Edit Profile", navController) }
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                val (inputLayout, saveButton) = createRefs()

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 5.dp)
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .constrainAs(inputLayout) {
                            top.linkTo(parent.top)
                        }
                ) {

                    var textFirstName by remember { mutableStateOf(TextFieldValue("")) }
                    var textLastName by remember { mutableStateOf(TextFieldValue("")) }
                    var textUserName by remember { mutableStateOf(TextFieldValue("")) }
                    var textEmail by remember { mutableStateOf(TextFieldValue("")) }
                    var textPassword by remember { mutableStateOf(TextFieldValue("")) }

                    ConstraintLayout(
                        modifier = Modifier
                            .align(alignment = CenterHorizontally)
                            .padding(it)
                    ) {
                        val (editButton, avatarView) = createRefs()
                        Image(
                            painter = painterResource(R.drawable.ic_avatar),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(145.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                                .constrainAs(avatarView) {
                                    top.linkTo(parent.top)
                                }
                        )
                        Button(
                            onClick = {},
                            modifier= Modifier
                                .size(40.dp)
                                .padding(bottom = 5.dp, end = 5.dp)
                                .constrainAs(editButton) {
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                            shape = CircleShape,
                            border= BorderStroke(0.dp, Color(0xff024040)),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xff024040)
                            )
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Edit Profile Picture",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(35.dp))
                    TextField(
                        value = textFirstName,
                        onValueChange = {
                            textFirstName = it
                        },
                        placeholder = { Text(text = "First Name") },
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

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textLastName,
                        onValueChange = {
                            textLastName = it
                        },
                        placeholder = { Text(text = "Last Name") },
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

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textUserName,
                        onValueChange = {
                            textUserName = it
                        },
                        placeholder = { Text(text = "User Name") },
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

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textEmail,
                        onValueChange = {
                            textEmail = it
                        },
                        placeholder = { Text(text = "Email") },
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

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textPassword,
                        onValueChange = {
                            textPassword = it
                        },
                        placeholder = { Text(text = "Password") },
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
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation()
                    )

                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                        .constrainAs(saveButton) {
                            bottom.linkTo(parent.bottom)
                        },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff024040)
                    )
                ){
                    Text(
                        text = "Save Changes",
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
fun EditProfileScreenPreview() {
    EditProfileScreen(navController = rememberNavController())
}