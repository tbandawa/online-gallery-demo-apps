package me.tbandawa.android.gallerydemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.R
import me.tbandawa.android.gallerydemo.ui.components.GalleryItem
import me.tbandawa.android.gallerydemo.ui.components.GalleryToolBar
import me.tbandawa.android.gallerydemo.ui.components.MessageBox
import me.tbandawa.android.gallerydemo.ui.components.Screen
import me.tbandawa.android.gallerydemo.ui.screens.*
import me.tbandawa.android.gallerydemo.ui.theme.GalleryDemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            GalleryDemoTheme {

                NavHost(navController, startDestination = Screen.Gallery.route) {

                    composable(route = Screen.Gallery.route) {
                        GalleryScreen(navController)
                    }

                    composable(route = Screen.Profile.route) {
                        ProfileScreen(navController)
                    }

                    composable(route = Screen.EditProfile.route) {
                        EditProfileScreen(navController)
                    }

                    composable(route = Screen.Settings.route) {
                        SettingsScreen(navController)
                    }

                    composable(route = Screen.Notifications.route) {
                        NotificationsScreen(navController)
                    }

                }

            }
        }
    }

}