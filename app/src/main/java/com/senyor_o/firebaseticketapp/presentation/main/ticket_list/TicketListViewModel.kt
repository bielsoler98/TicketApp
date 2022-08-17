package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.use_cases.*
import com.senyor_o.firebaseticketapp.presentation.edit.EditViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketListViewModel @Inject constructor(
    private val deleteTicket: DeleteTicket,
    private val insertTicket: InsertTicket,
    getTickets: GetTickets,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state: MutableState<TicketListState> = mutableStateOf(TicketListState())
    val state: State<TicketListState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("ticketTypeId")?.let {
            _state.value = _state.value.copy(ticketType = TicketType.values()[it])
            getTickets(_state.value.ticketType).onEach { tickets ->
                _state.value = state.value.copy(
                    tickets = tickets
                )
            }.launchIn(viewModelScope)
        }
        savedStateHandle.get<Int>("scrollTo")?.let {
            if(it != -1) {
                _state.value = _state.value.copy(
                    scrollTo = it
                )
            }
        }
    }

    fun onEvent(event: TicketListEvent) {
        when(event) {
            is TicketListEvent.OpenTicket -> viewModelScope.launch {
                insertTicket(event.ticket.copy(openedOn = System.currentTimeMillis()))
                _eventFlow.emit(UiEvent.TicketActionDone(event.ticket, "${event.ticket.title} opened!"))
            }
            is TicketListEvent.CloseTicket -> viewModelScope.launch {
                insertTicket(event.ticket.copy(closedOn = System.currentTimeMillis()))
                _eventFlow.emit(UiEvent.TicketActionDone(event.ticket, "${event.ticket.title} closed!"))
            }
            is TicketListEvent.DeleteTicket -> viewModelScope.launch {
                deleteTicket(event.ticket)
                _eventFlow.emit(UiEvent.TicketActionDone(event.ticket, "${event.ticket.title} deleted!"))
            }
            is TicketListEvent.ReopenTicket -> viewModelScope.launch {
                insertTicket(event.ticket.copy(closedOn = null))
                _eventFlow.emit(UiEvent.TicketActionDone(event.ticket, "${event.ticket.title} re-opened!"))
            }
            is TicketListEvent.MoveToToDo -> viewModelScope.launch {
                insertTicket(event.ticket.copy(openedOn = null))
                _eventFlow.emit(UiEvent.TicketActionDone(event.ticket, "${event.ticket.title} moved to ToDo list!"))
            }
            is TicketListEvent.UndoAction -> viewModelScope.launch {
                insertTicket(event.ticket)
            }
        }
    }

    sealed class UiEvent {
        data class TicketActionDone(val ticket: Ticket, val actionDescription: String): UiEvent()
    }
}