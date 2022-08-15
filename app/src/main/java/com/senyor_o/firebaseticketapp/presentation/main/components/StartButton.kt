package com.senyor_o.firebaseticketapp.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListEvent
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListViewModel
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TicketStateButton
import com.senyor_o.firebaseticketapp.ui.theme.DarkerButtonBlue
import com.senyor_o.firebaseticketapp.ui.theme.LightRed

@Composable
fun StartButton(
    modifier: Modifier,
    ticket: Ticket,
    ticketType: TicketType,
    onClick: (TicketListEvent) -> Unit
) = when(ticketType) {
    TicketType.ALL -> {}
    TicketType.TODO -> {
        TicketStateButton(
            modifier = modifier,
            text = "Delete",
            backgroundColor = LightRed,
            onClick = {
                onClick(TicketListEvent.DeleteTicket(ticket))
            }
        )
    }
    TicketType.OPEN -> {
        TicketStateButton(
            modifier = modifier,
            text = "To Do",
            backgroundColor = DarkerButtonBlue,
            onClick = {
                onClick(TicketListEvent.MoveToToDo(ticket))
            }
        )
    }
    TicketType.CLOSED -> {
        TicketStateButton(
            modifier = modifier,
            text = "Re-Open",
            backgroundColor = DarkerButtonBlue,
            onClick = {
                onClick(TicketListEvent.ReopenTicket(ticket))
            }
        )
    }
}