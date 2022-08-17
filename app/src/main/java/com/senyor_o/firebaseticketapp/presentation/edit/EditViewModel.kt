package com.senyor_o.firebaseticketapp.presentation.edit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.use_cases.GetTicket
import com.senyor_o.firebaseticketapp.domain.use_cases.InsertTicket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getTicket: GetTicket,
    private val insertTicket: InsertTicket,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<EditState> = mutableStateOf(EditState())
    val state: State<EditState> = _state

    private val _ticketTitle = mutableStateOf(TextFieldState())
    val ticketTitle: State<TextFieldState> = _ticketTitle

    private val _ticketDescription = mutableStateOf(TextFieldState())
    val ticketDescription: State<TextFieldState> = _ticketDescription

    private val _ticketCategory = mutableStateOf(TextFieldState())
    val ticketCategory: State<TextFieldState> = _ticketCategory

    private val _ticketColor = mutableStateOf(ColorState())
    val ticketColor: State<ColorState> = _ticketColor

    private val _ticketCreationDate = mutableStateOf(TextFieldState())
    val ticketCreationDate: State<TextFieldState> = _ticketCreationDate

    private val _ticketOpenDate = mutableStateOf(TextFieldState())
    val ticketOpenDate: State<TextFieldState> = _ticketOpenDate

    private val _ticketClosedDate = mutableStateOf(TextFieldState())
    val ticketClosedDate: State<TextFieldState> = _ticketClosedDate

    private val _dialogState = mutableStateOf(DialogState())
    val dialogState: State<DialogState> = _dialogState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentTicketId: Int? = null

    init {
        savedStateHandle.get<Int>("ticketId")?.let { ticketId ->
            if(ticketId != -1) {
                viewModelScope.launch {
                    getTicket(ticketId).also { ticket ->
                        currentTicketId = ticket.id
                        _state.value = _state.value.copy(
                            ticket = ticket
                        )
                        _ticketTitle.value = _ticketTitle.value.copy(
                            text = ticket.title
                        )
                        _ticketCategory.value = _ticketCategory.value.copy(
                            text = ticket.category
                        )
                        _ticketDescription.value = _ticketDescription.value.copy(
                            text = ticket.description
                        )
                        _ticketColor.value = _ticketColor.value.copy(
                            color = ticket.cardColor
                        )
                        _ticketCreationDate.value = _ticketCreationDate.value.copy(
                            text = ticket.getFormattedCreationDate()
                        )
                        _ticketOpenDate.value = _ticketOpenDate.value.copy(
                            text = ticket.getFormattedOpenDate()
                        )
                        _ticketClosedDate.value = _ticketClosedDate.value.copy(
                            text = ticket.getFormattedClosedDate()
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.EnteredCategory -> {
                _ticketCategory.value = _ticketCategory.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredColor -> {
                _ticketColor.value = _ticketColor.value.copy(
                    color = event.value
                )
            }
            is EditEvent.EnteredDescription -> {
                _ticketDescription.value = _ticketDescription.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredTitle -> {
                _ticketTitle.value = _ticketTitle.value.copy(
                    text = event.value
                )
            }
            EditEvent.InsertTicket -> {
                viewModelScope.launch {
                    if(ticketTitle.value.text.isNotEmpty()) {
                        insertTicket(
                            Ticket(
                                id = state.value.ticket?.id,
                                title = ticketTitle.value.text,
                                description = ticketDescription.value.text,
                                category = ticketCategory.value.text,
                                cardColor = ticketColor.value.color,
                                createdOn = state.value.ticket?.createdOn ?: System.currentTimeMillis(),
                                closedOn = state.value.ticket?.closedOn,
                                openedOn = state.value.ticket?.openedOn,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTicket)
                    } else {
                        _dialogState.value = _dialogState.value.copy(
                            errorMessage = "Title field can not be empty"
                        )
                    }
                }
            }
        }
    }

    fun hideErrorDialog() {
        _dialogState.value = _dialogState.value.copy(
            errorMessage = null
        )
    }

    sealed class UiEvent {
        object SaveTicket: UiEvent()
    }
}