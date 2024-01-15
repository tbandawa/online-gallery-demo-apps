package me.tbandawa.android.online.gallery.demo.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.R
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.data.viewmodel.AuthViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.MessageBox
import me.tbandawa.android.online.gallery.demo.ui.components.MessageType
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginComponent(
    navController: NavController
) {

    val authViewModel: AuthViewModel = koinViewModel()
    val userState by authViewModel.userResource.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    var textUserName by remember { mutableStateOf(TextFieldValue("")) }
    var textPassword by remember { mutableStateOf(TextFieldValue("")) }

    var isUserNameValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier
            .padding(bottom = 25.dp, start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        ) {
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

            when(userState) {
                is ResourceState.Loading -> {
                    isLoading = true
                    isError = false
                }
                is ResourceState.Success -> {
                    navController.navigate("home"){
                        launchSingleTop = true
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                }
                is ResourceState.Error -> {
                    val error = (userState as ResourceState.Error<*>).data!!
                    isLoading = false
                    isError = true

                    MessageBox(
                        type = MessageType.ERROR,
                        title = error.error!!,
                        message = error.messages.toString(),
                        visibility = isError
                    ) {
                        authViewModel.resetState()
                    }
                }
                is ResourceState.Empty -> {
                    isLoading = false
                    isError = false
                }
            }

            Spacer(modifier = Modifier.height(25.dp))
            TextField(
                value = textUserName,
                singleLine = true,
                enabled = !isLoading,
                onValueChange = { input ->
                    textUserName = input
                    isUserNameValid = input.text.isNotBlank()
                },
                placeholder = { Text(text = "User Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = Color(0xffF0F5F1),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = if (isUserNameValid) Color.Transparent else Color.Red,
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

            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    isUserNameValid = textUserName.text.isNotBlank()
                    isPasswordValid = textPassword.text.isNotBlank()
                    if (isUserNameValid && isPasswordValid) {
                        authViewModel.signInUser(username = textUserName.text, password = textPassword.text)
                    }
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