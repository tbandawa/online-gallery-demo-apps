package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.R

@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    NavigationBar(
        tonalElevation = 10.dp,
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(
                        id = R.drawable.ic_galleries
                    ),
                    "Galleries",
                    modifier = Modifier
                        .size(25.dp)
                )
            },
            selected = currentRoute == "galleries",
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.White,
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedIconColor = Color.Black.copy(0.4f),
                unselectedTextColor = Color.Black.copy(0.4f)
            ),
            onClick = {
                navController.navigate("galleries") {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(
                        id = R.drawable.ic_create
                    ),
                    "Galleries",
                    modifier = Modifier
                        .size(40.dp)
                )
            },
            selected = currentRoute == "create",
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.White,
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedIconColor = Color.Black.copy(0.4f),
                unselectedTextColor = Color.Black.copy(0.4f)
            ),
            onClick = {
                navController.navigate("create") {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(
                        id = R.drawable.ic_profile
                    ),
                    "Profile",
                    modifier = Modifier
                        .size(25.dp)
                )
            },
            selected = currentRoute == "profile",
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.White,
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                unselectedIconColor = Color.Black.copy(0.4f),
                unselectedTextColor = Color.Black.copy(0.4f)
            ),
            onClick = {
                navController.navigate("profile") {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}