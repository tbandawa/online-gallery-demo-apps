package me.tbandawa.android.online.gallery.demo.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.demo.ui.components.BottomNavigationBar

@Composable
fun HomeScreen() {

    val navController = rememberNavController()

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                MainNavigation(
                    navController = navController
                )
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    )
}

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = "galleries"
    ) {
        composable(route = "galleries") {
            Text(
                text = "Galleries",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )
        }
        composable(route = "create") {
            Text(
                text = "Create",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )
        }
        composable(route = "profile") {
            Text(
                text = "Profile",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            )
        }
        composable(route = "gallery/{id}") { backStackEntry ->
            val galleryId = backStackEntry.arguments?.getLong("id")
        }
        composable(route = "profile/{id}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getLong("id")
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}