package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.tbandawa.android.online.gallery.R

@Composable
fun LoadingState(
    message: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0x90024040)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CircularProgressIndicator(color = Color(0x90024040))
    }
}

@Composable
fun ErrorState(
    message: String,
    retry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.img_error),
            tint = Color(0x90f55050),
            contentDescription = "Error",
            modifier = Modifier
                .size(85.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0x90f55050)
        )
        TextButton(
            onClick = {
                retry()
            },
            modifier = Modifier
                .height(35.dp)
        ) {
            Text(
                text = "Retry",
                style = TextStyle(
                    color = Color(0xff024040),
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
fun DeleteState() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.img_delete),
            tint = Color(0x90024040),
            contentDescription = "Delete",
            modifier = Modifier
                .size(85.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Gallery Deleted",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0x90024040)
        )
    }
}

@Composable
fun NoResultsState() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.img_search),
            tint = Color(0x90024040),
            contentDescription = "Search",
            modifier = Modifier
                .size(85.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No results found",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0x90024040)
        )
    }
}

@Preview
@Composable
fun PreviewStates() {
    LoadingState(message = "Loading Results...")
    ErrorState(message = "Something went wrong", retry = { })
    DeleteState()
    NoResultsState()
}