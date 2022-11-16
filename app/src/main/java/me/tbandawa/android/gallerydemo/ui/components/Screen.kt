package me.tbandawa.android.gallerydemo.ui.components

sealed class Screen(val route: String) {
    object Gallery: Screen("gallery")
    object Profile: Screen("profile")
    object EditProfile: Screen("editProfile")
    object Settings: Screen("settings")
    object Notifications: Screen("notifications")
}