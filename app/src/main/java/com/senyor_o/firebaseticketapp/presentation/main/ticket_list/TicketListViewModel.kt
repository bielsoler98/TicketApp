package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import com.senyor_o.firebaseticketapp.domain.use_cases.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KFunction1

class TicketListViewModel @AssistedInject constructor(
    private val deleteTicket: DeleteTicket,
    private val insertTicket: InsertTicket,
    getTickets: GetTickets,
    @Assisted ticketType: TicketType,
) : ViewModel() {

    private val _state: MutableState<TicketListState> = mutableStateOf(TicketListState())
    val state: State<TicketListState> = _state

    init {
        getTickets(ticketType).onEach { tickets ->
            _state.value = state.value.copy(
                tickets = tickets
            )
        }.launchIn(viewModelScope)
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
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(ticketType: TicketType) : TicketListViewModel
    }

    companion object{
        fun provideFactory(
            assistedFactory: Factory,
            ticketType: TicketType,
        ) : ViewModelProvider.Factory =  object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(ticketType) as T
            }
        }
    }
}