package com.senyor_o.firebaseticketapp.presentation.main

sealed class Screen(val route: String) {

    object Home: Screen("home")

    object TicketList: Screen("ticket_list/{ticketTypeId}") {
        fun passId(ticketTypeId: Int): String{
            return "ticket_list/$ticketTypeId"
        }
    }

}
