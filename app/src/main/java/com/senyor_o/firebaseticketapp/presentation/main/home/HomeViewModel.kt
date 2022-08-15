package com.senyor_o.firebaseticketapp.presentation.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.use_cases.DeleteTicket
import com.senyor_o.firebaseticketapp.domain.use_cases.GetTickets
import com.senyor_o.firebaseticketapp.domain.use_cases.InsertTicket
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val deleteTicket: DeleteTicket,
    private val insertTicket: InsertTicket,
    getTickets: GetTickets
) : ViewModel() {

    private val _state: MutableState<HomeState> = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

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
}