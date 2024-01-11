package me.tbandawa.android.online.gallery.demo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.demo.ui.auth.AuthScreen
import me.tbandawa.android.online.gallery.demo.ui.screens.HomeScreen
import me.tbandawa.android.online.gallery.demo.ui.theme.OnlineGalleryDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var keepSplashOnScreen = true
        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, 3000L)

        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            val exitApp: () -> Unit = { this.finish() }

            OnlineGalleryDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "auth"
                    ) {
                        composable(route = "auth") {
                            AuthScreen(navController, exitApp)
                        }
                        composable(route = "home") {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    OnlineGalleryDemoTheme {
        AuthScreen(navController = rememberNavController(), exitApp = {})
    }
}
