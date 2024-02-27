package me.tbandawa.android.online.gallery.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.data.viewmodel.GalleryViewModel
import me.tbandawa.android.online.gallery.data.viewmodel.SplashViewModel
import me.tbandawa.android.online.gallery.demo.ui.auth.AuthScreen
import me.tbandawa.android.online.gallery.demo.ui.screens.GalleryScreen
import me.tbandawa.android.online.gallery.demo.ui.screens.HomeScreen
import me.tbandawa.android.online.gallery.demo.ui.theme.OnlineGalleryDemoTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val splashViewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashViewModel.getProfile()

        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.authState.value.complete
        }

        setContent {

            val galleryViewModel: GalleryViewModel = koinViewModel()
            val galleryState by galleryViewModel.galleryResource.collectAsState()
            val deleteState by galleryViewModel.galleryDeleteResource.collectAsState()

            val navController = rememberNavController()
            val authValue = splashViewModel.authState.collectAsState().value

            val navigateToGallery: (galleryId: Long, galleryUserId: Long, userId: Long) -> Unit = { galleryId, galleryUserId, userId ->
                navController.navigate("gallery/$galleryId/$galleryUserId/$userId")
            }

            val navigateToAuth: () -> Unit = {
                navController.navigate("auth") {
                    launchSingleTop = true
                    popUpTo("profile") {
                        inclusive = true
                    }
                }
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
                                AuthScreen(
                                    navController = navController
                                )
                            }

                            composable(route = "home") {
                                HomeScreen(
                                    navigateToGallery = navigateToGallery,
                                    navigateToAuth = navigateToAuth
                                )
                            }

                            composable(route = "gallery/{galleryId}/{galleryUserId}/{userId}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("galleryId")?.toLong()
                                val galleryUserId = backStackEntry.arguments?.getString("galleryUserId")?.toLong()
                                val userId = backStackEntry.arguments?.getString("userId")?.toLong()
                                GalleryScreen(
                                    navController = navController,
                                    galleryId = id!!,
                                    showDelete = (userId!! == galleryUserId!!),
                                    galleryState = galleryState,
                                    deleteState = deleteState,
                                    getGallery = { galleryId ->
                                        galleryViewModel.getGallery(galleryId)
                                    },
                                    deleteGallery = { galleryId ->
                                         galleryViewModel.deleteGallery(galleryId)
                                    },
                                    resetState = {
                                        galleryViewModel.resetState()
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    OnlineGalleryDemoTheme {
        AuthScreen(navController = rememberNavController())
    }
}
