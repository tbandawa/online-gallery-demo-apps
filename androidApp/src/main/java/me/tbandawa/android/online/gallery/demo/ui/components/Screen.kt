package me.tbandawa.android.online.gallery.demo.ui.components

sealed class Screen(val route: String) {
    data object Auth: Screen("auth")
    data object Gallery: Screen("gallery")
    data object Profile: Screen("profile")
    data object EditProfile: Screen("editProfile")
    data object Settings: Screen("settings")
}