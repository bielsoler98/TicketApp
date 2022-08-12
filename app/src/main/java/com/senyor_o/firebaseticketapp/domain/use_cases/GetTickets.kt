package com.senyor_o.firebaseticketapp.domain.use_cases

import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTickets @Inject constructor(
    private val repository: TicketRepository
) {
    operator fun invoke(ticketType: TicketType): Flow<List<Ticket>> =
        when(ticketType) {
            TicketType.ClosedTickets -> repository.getAllClosedTickets()
            TicketType.OpenTickets -> repository.getAllOpenTickets()
            TicketType.ToDoTickets -> repository.getAllToDoTickets()
        }

}