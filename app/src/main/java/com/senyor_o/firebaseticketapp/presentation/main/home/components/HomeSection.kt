package com.senyor_o.firebaseticketapp.presentation.main.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.presentation.components.TicketCard

@Composable
fun HomeSection(
    title: String,
    tickets: List<Ticket>,
    onItemClick: (Ticket, Int) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 7.5.dp),
        ) {
            tickets.forEachIndexed { index, ticket ->
                item {
                    TicketCard(
                        modifier = Modifier.height(216.dp),
                        title = ticket.title,
                        category = ticket.category,
                        date = when(ticket.getType()) {
                            TicketType.ALL -> ticket.getFormattedCreationDate()
                            TicketType.TODO -> ticket.getFormattedCreationDate()
                            TicketType.OPEN -> ticket.getFormattedOpenDate()
                            TicketType.CLOSED -> ticket.getFormattedClosedDate()
                        },
                        cardColor = ticket.cardColor.cardColorSet,
                        onClick = {
                            onItemClick(ticket, index)
                        }
                    )
                }
            }
        }
    }
}