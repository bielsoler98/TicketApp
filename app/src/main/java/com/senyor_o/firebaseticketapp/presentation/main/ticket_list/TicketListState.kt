package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import com.senyor_o.firebaseticketapp.domain.model.Ticket

data class TicketListState(
    val tickets: List<Ticket> = emptyList()
)