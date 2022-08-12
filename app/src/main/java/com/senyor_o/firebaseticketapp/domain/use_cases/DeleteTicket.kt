package com.senyor_o.firebaseticketapp.domain.use_cases


import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import javax.inject.Inject

class DeleteTicket @Inject constructor(
    private val repository: TicketRepository
) {
    suspend operator fun invoke(ticket: Ticket) {
        return repository.deleteTicket(ticket)
    }
}