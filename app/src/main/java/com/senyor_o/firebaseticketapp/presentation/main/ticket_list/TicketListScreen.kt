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
import com.senyor_o.firebaseticketapp.presentation.main.components.TicketSection
import com.senyor_o.firebaseticketapp.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun TicketListScreen(
    navController: NavController,
    viewModel: TicketListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val title = remember {
        mutableStateOf(state.ticketType?.title ?: "")
    }
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            TicketSection(
                title = title.value,
                tickets = state.tickets
            )
        }
    }
}