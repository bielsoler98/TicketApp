package com.senyor_o.firebaseticketapp.presentation.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.presentation.components.TicketCard
import com.senyor_o.firebaseticketapp.presentation.main.Screen
import com.senyor_o.firebaseticketapp.presentation.main.home.components.HomeSection
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.UiEvent.IndexFound -> {
                    navController.navigate(Screen.TicketList.passIds(event.ticket.getType().ordinal, event.index)) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if(state.allTickets.isNotEmpty()) {
                HomeSection(
                    title = "Last created tickets",
                    tickets = state.allTickets.asReversed(),
                    onItemClick = { ticket, _ ->
                        viewModel.getTicketIndex(ticket)
                    }
                )
            }
            if(state.toDoTickets.isNotEmpty()) {
                HomeSection(
                    title = "ToDo tickets",
                    tickets = state.toDoTickets,
                    onItemClick = { ticket, idx ->
                        navController.navigate(Screen.TicketList.passIds(ticket.getType().ordinal, idx)) {
                            navController.popBackStack()
                        }
                    }
                )
            }
            if(state.openTickets.isNotEmpty()) {
                HomeSection(
                    title = "Open tickets",
                    tickets = state.openTickets,
                    onItemClick = { ticket, idx ->
                        navController.navigate(Screen.TicketList.passIds(ticket.getType().ordinal, idx)) {
                            navController.popBackStack()
                        }
                    }
                )
            }
            if(state.closedTickets.isNotEmpty()) {
                HomeSection(
                    title = "Completed tickets",
                    tickets = state.closedTickets,
                    onItemClick = { ticket, idx ->
                        navController.navigate(Screen.TicketList.passIds(ticket.getType().ordinal, idx)) {
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
    }
}