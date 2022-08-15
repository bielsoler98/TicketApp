package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import com.senyor_o.firebaseticketapp.domain.model.TicketType

data class TicketTypeState (
    val ticketType: TicketType = TicketType.ALL
)