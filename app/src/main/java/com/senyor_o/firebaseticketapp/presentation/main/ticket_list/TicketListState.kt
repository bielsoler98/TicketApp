package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType

data class TicketListState(
    val tickets: List<Ticket> = emptyList(),
    val ticketType: TicketType = TicketType.ALL,
    val scrollTo: Int = 0
)