package me.tbandawa.android.online.gallery.demo.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.retry
import me.tbandawa.android.online.gallery.data.viewmodel.AuthViewModel
import me.tbandawa.android.online.gallery.data.viewmodel.GalleryViewModel
import me.tbandawa.android.online.gallery.data.viewmodel.ProfileViewModel
import me.tbandawa.android.online.gallery.demo.ui.components.BottomNavigationBar
import me.tbandawa.android.online.gallery.demo.ui.viewmodels.PagingGalleryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateToGallery: (galleryId: Long, galleryUserId: Long, userId: Long) -> Unit,
    navigateToAuth: () -> Unit
) {

    val navController = rememberNavController()

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                MainNavigation(
                    navController = navController,
                    navigateToGallery = { galleryId, galleryUserId, userId ->
                        navigateToGallery(galleryId, galleryUserId, userId)
                    },
                    navigateToAuth = navigateToAuth
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    )
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    navigateToGallery: (galleryId: Long, galleryUserId: Long, userId: Long) -> Unit,
    navigateToAuth: () -> Unit
) {

    val pagingGalleryViewModel: PagingGalleryViewModel = koinViewModel()
    val galleries = pagingGalleryViewModel.galleriesData.collectAsLazyPagingItems()

    val galleryViewModel: GalleryViewModel = koinViewModel()
    val galleryState by galleryViewModel.galleryResource.collectAsState()
    val galleriesState by galleryViewModel.galleriesResource.collectAsState()

    val profileViewModel: ProfileViewModel = koinViewModel()
    val userState by profileViewModel.userResource.collectAsState()
    val profileState by profileViewModel.profileResource.collectAsState()

    val authViewModel: AuthViewModel = koinViewModel()
    val logOutState by authViewModel.logOutResource.collectAsState()

    NavHost(
        navController,
        startDestination = "galleries"
    ) {

        composable(route = "galleries") {
            GalleriesScreen(
                navController = navController,
                galleries = galleries,
                retry = {
                    pagingGalleryViewModel.galleriesData.retry()
                },
                navigateToGallery = { galleryId, galleryUserId, userId ->
                    navigateToGallery(galleryId, galleryUserId, userId)
                }
            )
        }

        composable(route = "create") {
            CreateScreen(
                galleryState = galleryState,
                createGallery = { title, description, images ->
                    galleryViewModel.createGallery(title, description, images)
                },
                resetState = {
                    galleryViewModel.resetState()
                }
            )
        }

        composable(route = "profile") {
            ProfileScreen(
                navController = navController,
                navigateToGallery = { galleryId, galleryUserId, userId ->
                    navigateToGallery(galleryId, galleryUserId, userId)
                },
                navigateToAuth = navigateToAuth,
                profileState = profileState,
                signOutState = logOutState,
                getUserData = { profileViewModel.getUserData() },
                getProfile = { profileViewModel.getProfile() },
                singOutUser = {
                    authViewModel.signOutUser()
                }
            )
        }

        composable(route = "search") {
            SearchScreen(
                navController = navController,
                navigateToGallery = { galleryId, galleryUserId, userId ->
                    navigateToGallery(galleryId, galleryUserId, userId)
                },
                searchGalleries = { query ->
                    galleryViewModel.searchGallery(query)
                },
                galleriesState = galleriesState
            )
        }

        composable(route = "profile/edit") {
            EditProfileScreen(
                navController = navController,
                userState = userState,
                getUserData = { profileViewModel.getUserData()!! },
                resetState = { profileViewModel.resetState() },
                editUser = { firstname, lastname, username, email, password ->
                    profileViewModel.editUser(firstname, lastname, username, email, password)
                }
            )
        }

        composable(route = "profile/photo") {
            EditProfilePhotoScreen(
                navController = navController
            )
        }

        composable(route = "profile/{id}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getLong("id")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToGallery = {_, _, _ -> },
        navigateToAuth = { }
    )
}