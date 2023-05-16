package me.tbandawa.android.gallerydemo.ui.components

sealed class Screen(val route: String) {
    object Auth: Screen("auth")
    object Gallery: Screen("gallery")
    object Profile: Screen("profile")
    object EditProfile: Screen("editProfile")
    object Settings: Screen("settings")
}