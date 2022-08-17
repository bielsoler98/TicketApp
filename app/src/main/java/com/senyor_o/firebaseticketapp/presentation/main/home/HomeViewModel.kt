package com.senyor_o.firebaseticketapp.presentation.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.use_cases.GetTickets
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getTickets: GetTickets
) : ViewModel() {

    private val _state: MutableState<HomeState> = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getTickets(TicketType.ALL).onEach { tickets ->
            _state.value = state.value.copy(
                allTickets = tickets
            )
        }.launchIn(viewModelScope)

        getTickets(TicketType.TODO).onEach { tickets ->
            _state.value = state.value.copy(
                toDoTickets = tickets
            )
        }.launchIn(viewModelScope)

        getTickets(TicketType.OPEN).onEach { tickets ->
            _state.value = state.value.copy(
                openTickets = tickets
            )
        }.launchIn(viewModelScope)

        getTickets(TicketType.CLOSED).onEach { tickets ->
            _state.value = state.value.copy(
                closedTickets = tickets
            )
        }.launchIn(viewModelScope)
    }

    fun getTicketIndex(ticket: Ticket) {
        val ticketList: List<Ticket> = when(ticket.getType()) {
            TicketType.ALL -> _state.value.allTickets
            TicketType.TODO -> _state.value.toDoTickets
            TicketType.OPEN -> _state.value.openTickets
            TicketType.CLOSED -> _state.value.closedTickets
        }
        viewModelScope.launch {
            ticketList.forEachIndexed { index, it ->
                if (ticket == it ) {
                    _eventFlow.emit(UiEvent.IndexFound(ticket, index))
                }
            }
        }
    }

    sealed class UiEvent {
        data class IndexFound(val ticket: Ticket, val index: Int): UiEvent()
    }
}