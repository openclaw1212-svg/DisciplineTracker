package com.aashu.discipline.core.navigation

sealed class Destination(val route: String) {
    data object Home : Destination("home")
    data object Settings : Destination("settings")
}
