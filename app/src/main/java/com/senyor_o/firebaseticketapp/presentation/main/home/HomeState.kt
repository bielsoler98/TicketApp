package com.senyor_o.firebaseticketapp.presentation.main.home

import com.senyor_o.firebaseticketapp.domain.model.Ticket

data class HomeState(
    val allTickets: List<Ticket> = emptyList(),
    val toDoTickets: List<Ticket> = emptyList(),
    val openTickets: List<Ticket> = emptyList(),
    val closedTickets: List<Ticket> = emptyList(),
)