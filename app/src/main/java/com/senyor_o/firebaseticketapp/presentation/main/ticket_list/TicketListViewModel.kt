package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _typeState: MutableState<TicketTypeState> = mutableStateOf(TicketTypeState())
    val typeState: State<TicketTypeState> = _typeState

    init {
        savedStateHandle.get<Int>("ticketTypeId")?.let {
            _typeState.value = _typeState.value.copy(ticketType = TicketType.values()[it])
            getTickets(_typeState.value.ticketType).onEach { tickets ->
                _state.value = state.value.copy(
                    tickets = tickets
                )
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: TicketListEvent) {
        when(event) {
            is TicketListEvent.OpenTicket -> viewModelScope.launch {
                insertTicket(event.ticket.copy(openedOn = System.currentTimeMillis()))
            }
            is TicketListEvent.CloseTicket -> viewModelScope.launch {
                insertTicket(event.ticket.copy(closedOn = System.currentTimeMillis()))
            }
            is TicketListEvent.DeleteTicket -> viewModelScope.launch {
                deleteTicket(event.ticket)
            }
            is TicketListEvent.ReopenTicket -> viewModelScope.launch {
                insertTicket(event.ticket.copy(closedOn = null))
            }
            is TicketListEvent.MoveToToDo -> viewModelScope.launch {
                insertTicket(event.ticket.copy(openedOn = null))
            }
        }
    }
}