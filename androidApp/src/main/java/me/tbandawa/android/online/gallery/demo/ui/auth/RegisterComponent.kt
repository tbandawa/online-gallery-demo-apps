package me.tbandawa.android.online.gallery.demo.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.demo.ui.components.Screen

@Composable
fun RegisterComponent(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .padding(bottom = 25.dp, start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            var textFirstName by remember { mutableStateOf(TextFieldValue("")) }
            var textLastName by remember { mutableStateOf(TextFieldValue("")) }
            var textUserName by remember { mutableStateOf(TextFieldValue("")) }
            var textEmail by remember { mutableStateOf(TextFieldValue("")) }
            var textPassword by remember { mutableStateOf(TextFieldValue("")) }

            Spacer(modifier = Modifier.height(55.dp))
            Text(
                text = "Create account",
                style = TextStyle(
                    color = Color(0xff024040),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                modifier = Modifier.padding(start = 2.dp, end = 2.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                value = textFirstName,
                singleLine = true,
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
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xff024040),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color(0x90024040),
                    disabledLeadingIconColor = Color(0xff024040)
                )
            )

            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                value = textLastName,
                singleLine = true,
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
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xff024040),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color(0x90024040),
                    disabledLeadingIconColor = Color(0xff024040)
                )
            )

            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                value = textUserName,
                singleLine = true,
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
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xff024040),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color(0x90024040),
                    disabledLeadingIconColor = Color(0xff024040)
                )
            )

            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                value = textEmail,
                singleLine = true,
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
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xff024040),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color(0x90024040),
                    disabledLeadingIconColor = Color(0xff024040)
                )
            )

            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                value = textPassword,
                singleLine = true,
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
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xff024040),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color(0x90024040),
                    disabledLeadingIconColor = Color(0xff024040)
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.Gallery.route) {
                        launchSingleTop = true
                        popUpTo(Screen.Auth.route) {
                            inclusive = true
                        }
                    }
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff024040)
                )
            ){
                Text(
                    text = "Register",
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

@Preview
@Composable
fun RegisterComponentPreview() {
    RegisterComponent(navController = rememberNavController())
}