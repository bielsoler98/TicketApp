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
                            ticket = ticket,
                            title = ticket.title,
                            category = ticket.category,
                            description = ticket.description,
                            color = ticket.cardColor
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.EnteredCategory -> {
                _state.value = _state.value.copy(
                    category = event.value
                )
            }
            is EditEvent.EnteredColor -> {
                _state.value = _state.value.copy(
                    color = event.value
                )
            }
            is EditEvent.EnteredDescription -> {
                _state.value = _state.value.copy(
                    description = event.value
                )
            }
            is EditEvent.EnteredTitle -> {
                _state.value = _state.value.copy(
                    title = event.value
                )
            }
            EditEvent.InsertTicket -> {
                viewModelScope.launch {
                    if(state.value.title.isNotEmpty()) {
                        insertTicket(
                            Ticket(
                                id = state.value.ticket?.id,
                                title = state.value.title,
                                description = state.value.description,
                                category = state.value.category,
                                cardColor = state.value.color,
                                createdOn = state.value.ticket?.createdOn ?: System.currentTimeMillis(),
                                closedOn = state.value.ticket?.closedOn,
                                openedOn = state.value.ticket?.openedOn,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTicket)
                    } else {
                        _state.value = _state.value.copy(
                            errorMessage = "Title field can not be empty"
                        )
                    }
                }
            }
        }
    }

    fun hideErrorDialog() {
        _state.value = _state.value.copy(
            errorMessage = null
        )
    }

    sealed class UiEvent {
        object SaveTicket: UiEvent()
    }
}