package com.senyor_o.firebaseticketapp.presentation

sealed class Screen(val route: String) {

    object MainScreen: Screen("main_screen")

    object EditScreen: Screen("edit?{ticketType}")
}
