package me.tbandawa.android.online.gallery.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.data.viewmodel.SplashViewModel
import me.tbandawa.android.online.gallery.demo.ui.auth.AuthScreen
import me.tbandawa.android.online.gallery.demo.ui.screens.GalleryScreen
import me.tbandawa.android.online.gallery.demo.ui.screens.HomeScreen
import me.tbandawa.android.online.gallery.demo.ui.theme.OnlineGalleryDemoTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val userViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {


        installSplashScreen().setKeepOnScreenCondition {
            userViewModel.getProfile()
            userViewModel.authState.value.complete
        }

        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()
            val authValue = userViewModel.authState.collectAsState().value

            val navigateToGallery: (galleryId: Long) -> Unit = { galleryId ->
                navController.navigate("gallery/$galleryId")
            }

            OnlineGalleryDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (authValue.value != 0) {
                        NavHost(
                            navController = navController,
                            startDestination = if (authValue.value == 2) "auth" else "home"
                        ) {
                            composable(route = "auth") {
                                AuthScreen(navController)
                            }
                            composable(route = "home") {
                                HomeScreen(navigateToGallery)
                            }
                            composable(route = "gallery/{id}") { backStackEntry ->
                                val galleryId = backStackEntry.arguments?.getString("id")?.toLong()
                                GalleryScreen(
                                    navController = navController,
                                    galleryId = galleryId!!
                                )
                            }
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
        AuthScreen(navController = rememberNavController())
    }
}
