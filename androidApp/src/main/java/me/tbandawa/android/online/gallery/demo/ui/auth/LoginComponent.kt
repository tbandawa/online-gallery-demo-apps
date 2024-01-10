package me.tbandawa.android.online.gallery.demo.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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

@Composable
fun LoginComponent(
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

            var isLoading by remember { mutableStateOf(false) }
            var textUserName by remember { mutableStateOf(TextFieldValue("")) }
            var textPassword by remember { mutableStateOf(TextFieldValue("")) }

            Spacer(modifier = Modifier.height(55.dp))
            Text(
                text = "Log in to your account",
                style = TextStyle(
                    color = Color(0xff024040),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                ),
                modifier = Modifier.padding(start = 2.dp, end = 2.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                value = textUserName,
                singleLine = true,
                enabled = !isLoading,
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
                value = textPassword,
                singleLine = true,
                enabled = !isLoading,
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
                    /*navController.navigate(Screen.Gallery.route){
                        launchSingleTop = true
                        popUpTo(Screen.Auth.route) {
                            inclusive = true
                        }
                    }*/
                    isLoading = !isLoading
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp),
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
                        text = "Log in",
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

@Preview
@Composable
fun LoginComponentPreview() {
    LoginComponent(navController = rememberNavController())
}