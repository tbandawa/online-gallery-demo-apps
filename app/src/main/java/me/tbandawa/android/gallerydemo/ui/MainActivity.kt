package me.tbandawa.android.gallerydemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.ui.components.Screen
import me.tbandawa.android.gallerydemo.ui.screens.gallery.GalleryScreen
import me.tbandawa.android.gallerydemo.ui.screens.notifications.NotificationsScreen
import me.tbandawa.android.gallerydemo.ui.screens.profile.EditProfileScreen
import me.tbandawa.android.gallerydemo.ui.screens.profile.ProfileScreen
import me.tbandawa.android.gallerydemo.ui.screens.profile.SettingsScreen
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