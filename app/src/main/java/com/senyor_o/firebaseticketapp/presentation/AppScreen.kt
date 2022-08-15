package com.senyor_o.firebaseticketapp.presentation

sealed class AppScreen(val route: String) {

    object MainScreen: AppScreen("main_screen")

    object EditScreen: AppScreen("edit?ticketId={ticketId}") {
        fun passId(ticketId: Int?): String{
            return "edit?ticketId=$ticketId"
        }
    }
}
