package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.presentation.AppScreen
import com.senyor_o.firebaseticketapp.presentation.components.TicketCard
import com.senyor_o.firebaseticketapp.presentation.main.components.EndButton
import com.senyor_o.firebaseticketapp.presentation.main.components.StartButton
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TicketStateButton
import com.senyor_o.firebaseticketapp.ui.theme.DarkerButtonBlue
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue
import com.senyor_o.firebaseticketapp.ui.theme.LightRed
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@Composable
fun TicketListScreen(
    navController: NavController,
    viewModel: TicketListViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()

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
    LaunchedEffect(key1 = true) {
        listState.animateScrollToItem(state.scrollTo)
    }
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = state.ticketType.title,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(15.dp)
            )
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                state.tickets.forEach {
                    item {
                        TicketCard(
                            title = it.title,
                            category = it.category,
                            cardColor = it.cardColor.cardColorSet,
                            date = when(state.ticketType) {
                                TicketType.ALL -> it.getFormattedCreationDate()
                                TicketType.TODO -> it.getFormattedCreationDate()
                                TicketType.OPEN -> it.getFormattedOpenDate()
                                TicketType.CLOSED -> it.getFormattedClosedDate()
                            },
                            onClick = {
                                navController.navigate(AppScreen.EditScreen.passId(it.id))
                            },
                            startButton = { modifier ->
                                StartButton(
                                    modifier = modifier,
                                    ticket = it,
                                    ticketType = state.ticketType,
                                    onClick = viewModel::onEvent
                                )
                            },
                            endButton = { modifier ->
                                EndButton(
                                    modifier = modifier,
                                    ticket = it,
                                    ticketType = state.ticketType,
                                    onClick = viewModel::onEvent
                                )
                            }
                        )
                    }
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}