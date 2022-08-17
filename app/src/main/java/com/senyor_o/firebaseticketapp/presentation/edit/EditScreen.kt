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
    val title = viewModel.ticketTitle.value
    val description = viewModel.ticketDescription.value
    val category = viewModel.ticketCategory.value
    val colorState = viewModel.ticketColor.value
    val ticketCreationDate = viewModel.ticketCreationDate.value
    val ticketOpenDate = viewModel.ticketOpenDate.value
    val ticketCloseDate = viewModel.ticketClosedDate.value
    val dialogMessage = viewModel.dialogState.value

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
                        title = title.text,
                        category = category.text,
                        cardColor = colorState.color.cardColorSet
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TicketColorInput(
                            viewModel.ticketColor,
                            CardColor.RED,
                            onClick = viewModel::onEvent
                        )
                        TicketColorInput(
                            viewModel.ticketColor,
                            CardColor.BLUE,
                            onClick = viewModel::onEvent
                        )
                        TicketColorInput(
                            viewModel.ticketColor,
                            CardColor.GREEN,
                            onClick = viewModel::onEvent
                        )
                        TicketColorInput(
                            viewModel.ticketColor,
                            CardColor.YELLOW,
                            onClick = viewModel::onEvent
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TicketInputText(
                        title = "Title",
                        text = title.text,
                        maxChar = 20,
                        onTextChange = {
                            viewModel.onEvent(EditEvent.EnteredTitle(it))
                        }
                    )
                    TicketInputText(
                        title = "Category",
                        text = category.text,
                        maxChar = 20,
                        onTextChange = {
                            viewModel.onEvent(EditEvent.EnteredCategory(it))
                        }
                    )
                    TicketInputText(
                        title = "Description",
                        modifier = Modifier
                            .height(120.dp),
                        text = description.text,
                        maxChar = 180,
                        maxLines = 5,
                        singleLine = false,
                        onTextChange = {
                            viewModel.onEvent(EditEvent.EnteredDescription(it))
                        }
                    )
                    if(ticketCreationDate.text.isNotEmpty()) {
                        TicketInputText(
                            title = "Creation Date",
                            text = ticketCreationDate.text,
                            enabled = false
                        )
                    }
                    if(ticketOpenDate.text.isNotEmpty()) {
                        TicketInputText(
                            title = "Open Date",
                            text = ticketOpenDate.text,
                            enabled = false
                        )
                    }
                    if(ticketCloseDate.text.isNotEmpty()) {
                        TicketInputText(
                            title = "Completed Date",
                            text = ticketCloseDate.text,
                            enabled = false
                        )
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
                        "SaveTicket"
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
            if(dialogMessage.errorMessage != null){
                EventDialog(
                    errorMessage = dialogMessage.errorMessage,
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