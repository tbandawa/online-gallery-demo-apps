package me.tbandawa.android.online.gallery.demo.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.R
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState
import me.tbandawa.android.online.gallery.data.viewmodel.AuthViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.MessageBox
import me.tbandawa.android.online.gallery.demo.ui.components.MessageType
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterComponent(
    navController: NavController
) {

    val authViewModel: AuthViewModel = koinViewModel()
    val userState by authViewModel.userResource.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var showPassword by remember { mutableStateOf(false) }
    var errorTitle by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var textFirstName = rememberSaveable { mutableStateOf("") }
    var textLastName = rememberSaveable { mutableStateOf("") }
    var textUserName = rememberSaveable { mutableStateOf("") }
    var textEmail = rememberSaveable { mutableStateOf("") }
    var textPassword = rememberSaveable { mutableStateOf("") }

    var isFirstNameValid by remember { mutableStateOf(true) }
    var isLastNameValid by remember { mutableStateOf(true) }
    var isUserNameValid by remember { mutableStateOf(true) }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    when(userState) {
        is ResourceState.Loading -> {
            isLoading = true
            isError = false
        }
        is ResourceState.Success -> {
            navController.navigate("home") {
                launchSingleTop = true
                popUpTo("auth") {
                    inclusive = true
                }
            }
            authViewModel.resetState()
        }
        is ResourceState.Error -> {
            val error = (userState as ResourceState.Error<*>).data!!
            isLoading = false
            isError = true
            errorTitle = error.error!!
            errorMessage = ""
            for(index in error.messages!!.indices) {
                val newLine = if (index == (error.messages!!.size - 1)) "" else "\n"
                errorMessage += "${error.messages!![index]}$newLine"
            }
        }
        is ResourceState.Empty -> {
            isLoading = false
            isError = false
        }
    }

    Surface(
        modifier = Modifier
            .padding(bottom = 25.dp, start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
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

            MessageBox(
                type = MessageType.ERROR,
                title = errorTitle,
                message = errorMessage,
                visibility = isError
            ) {
                authViewModel.resetState()
            }

            Spacer(modifier = Modifier.height(25.dp))
            BasicTextField(
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (isFirstNameValid) Color(0xff024040) else Color(0xfff55050)
                ),
                value = textFirstName.value,
                singleLine = true,
                enabled = !isLoading,
                onValueChange = { input ->
                    textFirstName.value = input
                    isFirstNameValid = input.isNotBlank()
                },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isFirstNameValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    ) {
                        if (textFirstName.value.isEmpty()) {
                            Text(
                                text = "Firstname",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = if (isFirstNameValid) Color(0x90024040) else Color(0xfff55050)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(25.dp))
            BasicTextField(
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (isLastNameValid) Color(0xff024040) else Color(0xfff55050)
                ),
                value = textLastName.value,
                singleLine = true,
                enabled = !isLoading,
                onValueChange = { input ->
                    textLastName.value = input
                    isLastNameValid = input.isNotBlank()
                },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isLastNameValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    ) {
                        if (textLastName.value.isEmpty()) {
                            Text(
                                text = "Lastname",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = if (isLastNameValid) Color(0x90024040) else Color(0xfff55050)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(25.dp))
            BasicTextField(
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (isUserNameValid) Color(0xff024040) else Color(0xfff55050)
                ),
                value = textUserName.value,
                singleLine = true,
                enabled = !isLoading,
                onValueChange = { input ->
                    textUserName.value = input
                    isUserNameValid = input.isNotBlank()
                },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isUserNameValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                    ) {
                        if (textUserName.value.isEmpty()) {
                            Text(
                                text = "Username",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = if (isUserNameValid) Color(0x90024040) else Color(0xfff55050)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(25.dp))
            BasicTextField(
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (isEmailValid) Color(0xff024040) else Color(0xfff55050)
                ),
                value = textEmail.value,
                singleLine = true,
                enabled = !isLoading,
                onValueChange = { input ->
                    textEmail.value = input
                    isEmailValid = input.isNotBlank()
                },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isEmailValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(10.dp)
                    ) {
                        if (textEmail.value.isEmpty()) {
                            Text(
                                text = "Email",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = if (isEmailValid) Color(0x90024040) else Color(0xfff55050)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(25.dp))
            BasicTextField(
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = if (isPasswordValid) Color(0xff024040) else Color(0xfff55050)
                ),
                value = textPassword.value,
                singleLine = true,
                enabled = !isLoading,
                onValueChange = { input ->
                    textPassword.value = input
                    isPasswordValid = input.isNotBlank()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                decorationBox = { innerTextField ->
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xffF0F5F1),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = if (isPasswordValid) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 7.dp)
                    ) {
                        val (text, icon) = createRefs()
                        Box(
                            modifier = Modifier
                                .constrainAs(text) {
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                    end.linkTo(icon.start)
                                    bottom.linkTo(parent.bottom)
                                    width = Dimension.fillToConstraints
                                }
                        ) {
                            if (textPassword.value.isEmpty()) {
                                Text(
                                    text = "Password",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = if (isPasswordValid) Color(0x90024040) else Color(0xfff55050)
                                )
                            }
                            innerTextField()
                        }
                        Box(
                            modifier = Modifier
                                .constrainAs(icon) {
                                    start.linkTo(text.end)
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                                .size(25.dp)
                        ) {
                            if (showPassword) {
                                IconButton(onClick = { showPassword = false }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_visibility_on),
                                        tint = Color(0xff024040),
                                        contentDescription = "Show Password"
                                    )
                                }
                            } else {
                                IconButton(onClick = { showPassword = true }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_visibility_off),
                                        tint = Color(0xff024040),
                                        contentDescription = "Hide Password"
                                    )
                                }
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(25.dp))
            Button(
                onClick = {
                    isFirstNameValid = textFirstName.value.isNotBlank()
                    isLastNameValid = textLastName.value.isNotBlank()
                    isUserNameValid = textUserName.value.isNotBlank()
                    isEmailValid = textEmail.value.isNotBlank()
                    isPasswordValid = textPassword.value.isNotBlank()
                    if (isFirstNameValid && isLastNameValid && isEmailValid && isUserNameValid && isPasswordValid) {
                        authViewModel.signUpUser(
                            firstname = textFirstName.value,
                            lastname = textLastName.value,
                            username = textUserName.value,
                            email = textEmail.value,
                            password = textPassword.value
                        )
                    }
                },
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
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
                        text = "Register",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

@Preview
@Composable
fun RegisterComponentPreview() {
    RegisterComponent(navController = rememberNavController())
}