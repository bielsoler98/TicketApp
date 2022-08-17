package com.senyor_o.firebaseticketapp.presentation.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.senyor_o.firebaseticketapp.domain.model.CardColor
import com.senyor_o.firebaseticketapp.presentation.components.TicketCard
import com.senyor_o.firebaseticketapp.presentation.edit.components.*
import com.senyor_o.firebaseticketapp.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun EditScreen(
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is EditViewModel.UiEvent.SaveTicket -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        contentColor = DeepBlue,
        topBar = {
            EditTopBar(
                title = if(viewModel.currentTicketId != null) {
                    "Edit Ticket"
                } else {
                    "New Ticket"
                },
                onNavigationClicked = {
                    navController.navigateUp()
                }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .background(DeepBlue)
                .fillMaxSize()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DeepBlue)

            ) {
                val (infoFields, saveButton) = createRefs()
                Column(
                    modifier = Modifier
                        .background(DeepBlue)
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 56.dp)
                        .constrainAs(infoFields) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(saveButton.top)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TicketCard(
                        title = state.title,
                        category = state.category,
                        cardColor = state.color.cardColorSet
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TicketColorInput(
                            viewModel.state,
                            CardColor.RED,
                            onClick = viewModel::onEvent
                        )
                        TicketColorInput(
                            viewModel.state,
                            CardColor.BLUE,
                            onClick = viewModel::onEvent
                        )
                        TicketColorInput(
                            viewModel.state,
                            CardColor.GREEN,
                            onClick = viewModel::onEvent
                        )
                        TicketColorInput(
                            viewModel.state,
                            CardColor.YELLOW,
                            onClick = viewModel::onEvent
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TicketInputText(
                        title = "Title",
                        text = state.title,
                        maxChar = 20,
                        onTextChange = {
                            viewModel.onEvent(EditEvent.EnteredTitle(it))
                        }
                    )
                    TicketInputText(
                        title = "Category",
                        text = state.category,
                        maxChar = 20,
                        onTextChange = {
                            viewModel.onEvent(EditEvent.EnteredCategory(it))
                        }
                    )
                    TicketInputText(
                        title = "Description",
                        modifier = Modifier
                            .height(120.dp),
                        text = state.description,
                        maxChar = 180,
                        maxLines = 5,
                        singleLine = false,
                        onTextChange = {
                            viewModel.onEvent(EditEvent.EnteredDescription(it))
                        }
                    )
                    if(state.ticket != null) {
                        if(state.ticket.getFormattedCreationDate().isNotEmpty()) {
                            TicketInputText(
                                title = "Creation Date",
                                text = state.ticket.getFormattedCreationDate(),
                                enabled = false
                            )
                        }
                        if(state.ticket.getFormattedOpenDate().isNotEmpty()) {
                            TicketInputText(
                                title = "Open Date",
                                text = state.ticket.getFormattedOpenDate(),
                                enabled = false
                            )
                        }
                        if(state.ticket.getFormattedClosedDate().isNotEmpty()) {
                            TicketInputText(
                                title = "Completed Date",
                                text = state.ticket.getFormattedClosedDate(),
                                enabled = false
                            )
                        }
                    }
                }
                RoundedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .constrainAs(saveButton) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = if(viewModel.currentTicketId != null) {
                        "Save Ticket"
                    } else {
                        "Create Ticket"
                    },
                    onClick = {
                        viewModel.onEvent(
                            EditEvent.InsertTicket
                        )
                    }
                )
            }
            if(state.errorMessage != null){
                EventDialog(
                    errorMessage = state.errorMessage,
                    onDismiss = viewModel::hideErrorDialog
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewEdit() {
    FirebaseTicketAppTheme {
        EditScreen(navController = NavController(LocalContext.current))
    }
}