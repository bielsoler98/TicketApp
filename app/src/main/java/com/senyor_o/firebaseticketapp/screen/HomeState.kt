package com.senyor_o.firebaseticketapp.screen

import com.senyor_o.firebaseticketapp.Ticket

data class HomeState(
    val tickets: MutableList<Ticket>,
    val showOpenTickets: Boolean = true
)