package me.tbandawa.android.gallerydemo.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import me.tbandawa.android.gallerydemo.ui.components.Screen

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

            var textUserName by remember { mutableStateOf(TextFieldValue("")) }
            var textPassword by remember { mutableStateOf(TextFieldValue("")) }

            Spacer(modifier = Modifier.height(25.dp))
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

            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    navController.navigate(Screen.Gallery.route){
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
                    backgroundColor = Color(0xff024040)
                )
            ){
                Text(
                    text = "Log in",
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
fun LoginComponentPreview() {
    LoginComponent(navController = rememberNavController())
}