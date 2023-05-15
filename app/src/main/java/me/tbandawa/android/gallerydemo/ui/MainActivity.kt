package me.tbandawa.android.gallerydemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.gallerydemo.ui.components.Screen
import me.tbandawa.android.gallerydemo.ui.screens.auth.AuthScreen
import me.tbandawa.android.gallerydemo.ui.screens.gallery.GalleryScreen
import me.tbandawa.android.gallerydemo.ui.screens.notifications.NotificationsScreen
import me.tbandawa.android.gallerydemo.ui.screens.profile.EditProfileScreen
import me.tbandawa.android.gallerydemo.ui.screens.profile.ProfileScreen
import me.tbandawa.android.gallerydemo.ui.screens.profile.SettingsScreen
import me.tbandawa.android.gallerydemo.ui.screens.splash.SplashViewModel
import me.tbandawa.android.gallerydemo.ui.theme.GalleryDemoTheme

class MainActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{viewModel.isLoading.value}

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            GalleryDemoTheme {

                NavHost(navController, startDestination = Screen.Auth.route) {

                    composable(route = Screen.Auth.route) {
                        AuthScreen()
                    }

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