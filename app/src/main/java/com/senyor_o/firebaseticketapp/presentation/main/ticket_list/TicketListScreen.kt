package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.presentation.AppScreen
import com.senyor_o.firebaseticketapp.presentation.main.components.TicketSection
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TicketStateButton
import com.senyor_o.firebaseticketapp.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun TicketListScreen(
    navController: NavController,
    viewModel: TicketListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val typeState = viewModel.typeState.value
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            TicketSection(
                title = typeState.ticketType.title,
                tickets = state.tickets,
                onItemClicked = {
                    navController.navigate(AppScreen.EditScreen.passId(it))
                },
                startButton = { modifier, ticket ->
                    StartButton(
                        modifier = modifier,
                        ticket = ticket,
                        ticketType = typeState.ticketType,
                        viewModel = viewModel
                    )

                },
                endButton = { modifier, ticket ->
                    EndButton(
                        modifier = modifier,
                        ticket = ticket,
                        ticketType = typeState.ticketType,
                        viewModel = viewModel
                    )
                }
            )
        }
    }
}

@Composable
fun StartButton(
    modifier: Modifier,
    ticket: Ticket,
    ticketType: TicketType,
    viewModel: TicketListViewModel
) = when(ticketType) {
    TicketType.ALL -> {}
    TicketType.TODO -> {
        TicketStateButton(
            modifier = modifier,
            text = "Delete",
            backgroundColor = LightRed,
            onClick = {
                viewModel.onEvent(TicketListEvent.DeleteTicket(ticket))
            }
        )
    }
    TicketType.OPEN -> {
        TicketStateButton(
            modifier = modifier,
            text = "To Do",
            backgroundColor = DarkerButtonBlue,
            onClick = {
                viewModel.onEvent(TicketListEvent.MoveToToDo(ticket))
            }
        )
    }
    TicketType.CLOSED -> {
        TicketStateButton(
            modifier = modifier,
            text = "Re-Open",
            backgroundColor = DarkerButtonBlue,
            onClick = {
                viewModel.onEvent(TicketListEvent.ReopenTicket(ticket))
            }
        )
    }
}

@Composable
fun EndButton(
    modifier: Modifier,
    ticket: Ticket,
    ticketType: TicketType,
    viewModel: TicketListViewModel
) = when(ticketType) {
    TicketType.ALL -> {}
    TicketType.TODO -> {
        TicketStateButton(
            modifier = modifier,
            text = "Open",
            backgroundColor = DarkerButtonBlue,
            onClick = {
                viewModel.onEvent(TicketListEvent.OpenTicket(ticket))
            }
        )
    }
    TicketType.OPEN -> {
        TicketStateButton(
            modifier = modifier,
            text = "Close",
            backgroundColor = LightRed,
            onClick = {
                viewModel.onEvent(TicketListEvent.CloseTicket(ticket))
            }
        )
    }
    TicketType.CLOSED -> {
        TicketStateButton(
            modifier = modifier,
            text = "Delete",
            backgroundColor = LightRed,
            onClick = {
                viewModel.onEvent(TicketListEvent.DeleteTicket(ticket))
            }
        )
    }
}