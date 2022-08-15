package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.presentation.AppScreen
import com.senyor_o.firebaseticketapp.presentation.edit.EditViewModel
import com.senyor_o.firebaseticketapp.presentation.main.components.TicketSection
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TicketStateButton
import com.senyor_o.firebaseticketapp.ui.theme.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun TicketListScreen(
    navController: NavController,
    viewModel: TicketListViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val state = viewModel.state.value
    val typeState = viewModel.typeState.value
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is TicketListViewModel.UiEvent.TicketActionDone -> {
                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.actionDescription,
                        actionLabel = "Undo"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            viewModel.onEvent(TicketListEvent.UndoAction(event.ticket))
                        }
                    }
                }
            }
        }
    }

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
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
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