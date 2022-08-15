package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType

data class TicketListState(
    val ticketType: TicketType? = null,
    val tickets: List<Ticket> = emptyList()
)