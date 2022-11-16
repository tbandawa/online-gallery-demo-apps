package me.tbandawa.android.gallerydemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.R
import me.tbandawa.android.gallerydemo.ui.components.NavigationToolbar
import me.tbandawa.android.gallerydemo.ui.components.NotificationItem

@Composable
fun NotificationsScreen(
    navController: NavController
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Scaffold(
            topBar = { NavigationToolbar(title = "Settings", navController) }
        ) {

            val notificationStatuses = listOf(false, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                items(notificationStatuses) { notificationStatus ->
                    NotificationItem(
                        painter = painterResource(id = R.drawable.free_1),
                        message = "to your upcoming projects with this sci-fi inspired collection.",
                        user = "Tendai Bandawa",
                        date = "5 Nov 2022",
                        isRead = notificationStatus
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreen(navController = rememberNavController())
}
