package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import com.senyor_o.firebaseticketapp.domain.model.Ticket

sealed class TicketListEvent {

    data class CloseTicket(val ticket: Ticket): TicketListEvent()

    data class OpenTicket(val ticket: Ticket): TicketListEvent()

    data class ReopenTicket(val ticket: Ticket): TicketListEvent()

    data class DeleteTicket(val ticket: Ticket): TicketListEvent()

    data class MoveToToDo(val ticket: Ticket): TicketListEvent()
}
