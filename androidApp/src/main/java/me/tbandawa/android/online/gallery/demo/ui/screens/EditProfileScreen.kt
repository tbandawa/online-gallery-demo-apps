package me.tbandawa.android.online.gallery.demo.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.R
import me.tbandawa.android.online.gallery.demo.ui.components.NavigationToolbar

@Composable
fun EditProfileScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 15.dp, end = 15.dp)
    ) {

        var isLoading by remember { mutableStateOf(false) }
        var isError by remember { mutableStateOf(false) }
        var showPassword by remember { mutableStateOf(false) }
        var textFirstName by remember { mutableStateOf(TextFieldValue("")) }
        var textLastName by remember { mutableStateOf(TextFieldValue("")) }
        var textUserName by remember { mutableStateOf(TextFieldValue("")) }
        var textEmail by remember { mutableStateOf(TextFieldValue("")) }
        var textPassword by remember { mutableStateOf(TextFieldValue("")) }

        var isFirstNameValid by remember { mutableStateOf(true) }
        var isLastNameValid by remember { mutableStateOf(true) }
        var isUserNameValid by remember { mutableStateOf(true) }
        var isEmailValid by remember { mutableStateOf(true) }
        var isPasswordValid by remember { mutableStateOf(true) }

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
                            painter = painterResource(R.drawable.ic_launcher_foreground),
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
                            onClick = {

                            },
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
                                containerColor = Color(0xff024040)
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
                        singleLine = true,
                        onValueChange = { input ->
                            textFirstName = input
                            isFirstNameValid = input.text.isNotBlank()
                        },
                        placeholder = { Text(text = "First Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = if (isFirstNameValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff024040),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedPlaceholderColor = if (isFirstNameValid) Color(0x90024040) else Color(0xfff55050),
                            disabledLeadingIconColor = Color(0xff024040)
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textLastName,
                        singleLine = true,
                        enabled = !isLoading,
                        onValueChange = { input ->
                            textLastName = input
                            isLastNameValid = input.text.isNotBlank()
                        },
                        placeholder = { Text(text = "Last Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = if (isLastNameValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff024040),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedPlaceholderColor = if (isLastNameValid) Color(0x90024040) else Color(0xfff55050),
                            disabledLeadingIconColor = Color(0xff024040)
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textUserName,
                        onValueChange = { input ->
                            textUserName = input
                        },
                        enabled = false,
                        placeholder = { Text(text = "User Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff024040),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedPlaceholderColor = if (isUserNameValid) Color(0x90024040) else Color(0xfff55050),
                            disabledLeadingIconColor = Color(0xff024040)
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textEmail,
                        onValueChange = { input ->
                            textEmail = input
                        },
                        enabled = false,
                        placeholder = { Text(text = "Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = if (isEmailValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff024040),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedPlaceholderColor = if (isEmailValid) Color(0x90024040) else Color(0xfff55050),
                            disabledLeadingIconColor = Color(0xff024040)
                        )
                    )

                    Spacer(modifier = Modifier.height(25.dp))
                    TextField(
                        value = textPassword,
                        singleLine = true,
                        enabled = !isLoading,
                        onValueChange = { input ->
                            textPassword = input
                            isPasswordValid = input.text.isNotBlank()
                        },
                        placeholder = { Text(text = "Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(
                                width = 2.dp,
                                color = if (isPasswordValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color(0xff024040),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedPlaceholderColor = if (isPasswordValid) Color(0x90024040) else Color(0xfff55050),
                            disabledLeadingIconColor = Color(0xff024040)
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (showPassword) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            if (showPassword) {
                                IconButton(onClick = { showPassword = false }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_visibility_on),
                                        contentDescription = "Show Password"
                                    )
                                }
                            } else {
                                IconButton(onClick = { showPassword = true }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_visibility_off),
                                        contentDescription = "Hide Password"
                                    )
                                }
                            }
                        }
                    )

                }

                Button(
                    onClick = {
                        isFirstNameValid = textFirstName.text.isNotBlank()
                        isLastNameValid = textLastName.text.isNotBlank()
                        isPasswordValid = textPassword.text.isNotBlank()
                        if (isFirstNameValid && isLastNameValid && isEmailValid && isUserNameValid && isPasswordValid) {
                            /*authViewModel.signUpUser(
                                firstname = textFirstName.text,
                                lastname = textLastName.text,
                                username = textUserName.text,
                                email = textEmail.text,
                                password = textPassword.text
                            )*/
                        }
                    },
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
                        .constrainAs(saveButton) {
                            bottom.linkTo(parent.bottom)
                        },
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
                            text = "Save Changes",
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

@Preview
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreen(navController = rememberNavController())
}