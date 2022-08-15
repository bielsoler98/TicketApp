package com.senyor_o.firebaseticketapp.presentation.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.presentation.AppScreen
import com.senyor_o.firebaseticketapp.presentation.components.TicketCard
import com.senyor_o.firebaseticketapp.presentation.main.components.EndButton
import com.senyor_o.firebaseticketapp.presentation.main.components.StartButton
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HomeSection(
                title = "Last created tickets",
                tickets = state.allTickets.asReversed(),
                onItemClick = {
                    navController.navigate(AppScreen.EditScreen.passId(it))
                }
            )
            HomeSection(
                title = "ToDo tickets",
                tickets = state.toDoTickets,
                onItemClick = {
                    navController.navigate(AppScreen.EditScreen.passId(it))
                }
            )
            HomeSection(
                title = "Open tickets",
                tickets = state.openTickets,
                onItemClick = {
                    navController.navigate(AppScreen.EditScreen.passId(it))
                }
            )
            HomeSection(
                title = "Completed tickets",
                tickets = state.closedTickets,
                onItemClick = {
                    navController.navigate(AppScreen.EditScreen.passId(it))
                }
            )
        }
    }
}

@Composable
fun HomeSection(
    title: String,
    tickets: List<Ticket>,
    onItemClick: (Int?) -> Unit
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
            tickets.forEach {
                item {
                    TicketCard(
                        modifier = Modifier.height(216.dp),
                        title = it.title,
                        category = it.category,
                        cardColor = it.cardColor.cardColorSet,
                        onClick = {
                            onItemClick(it.id)

                        },
                        startButton = { modifier ->
                            StartButton(
                                modifier = modifier,
                                ticket = it,
                                ticketType = it.getType(),
                                onClick = {}
                            )
                        },
                        endButton = { modifier ->
                            EndButton(
                                modifier = modifier,
                                ticket = it,
                                ticketType = it.getType(),
                                onClick = {}
                            )
                        }
                    )
                }
            }
        }
    }
}