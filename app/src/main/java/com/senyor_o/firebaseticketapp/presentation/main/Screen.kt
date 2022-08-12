package com.senyor_o.firebaseticketapp.presentation.main

sealed class Screen(val route: String) {

    object Home: Screen("home")

    object OpenTickets: Screen("open")

    object ClosedTickets: Screen("closed")

    object ToDoTickets: Screen("to_do")

}
