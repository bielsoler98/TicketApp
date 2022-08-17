package com.senyor_o.firebaseticketapp.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListEvent
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListViewModel
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TicketStateButton
import com.senyor_o.firebaseticketapp.ui.theme.DarkerButtonBlue
import com.senyor_o.firebaseticketapp.ui.theme.LightGreen1
import com.senyor_o.firebaseticketapp.ui.theme.LightRed

@Composable
fun EndButton(
    modifier: Modifier,
    ticket: Ticket,
    ticketType: TicketType,
    onClick: (TicketListEvent) -> Unit
) = when(ticketType) {
    TicketType.ALL -> {}
    TicketType.TODO -> {
        TicketStateButton(
            modifier = modifier,
            text = "Open",
            backgroundColor = DarkerButtonBlue,
            onClick = {
                onClick(TicketListEvent.OpenTicket(ticket))
            }
        )
    }
    TicketType.OPEN -> {
        TicketStateButton(
            modifier = modifier,
            text = "Complete",
            backgroundColor = LightGreen1,
            onClick = {
                onClick(TicketListEvent.CloseTicket(ticket))
            }
        )
    }
    TicketType.CLOSED -> {
        TicketStateButton(
            modifier = modifier,
            text = "Delete",
            backgroundColor = LightRed,
            onClick = {
                onClick(TicketListEvent.DeleteTicket(ticket))
            }
        )
    }
}