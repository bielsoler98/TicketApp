package com.senyor_o.firebaseticketapp.domain.use_cases

import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import javax.inject.Inject


class GetTicket @Inject constructor(
    private val repository: TicketRepository
) {
    suspend operator fun invoke(id: Int): Ticket {
        return repository.getTicketById(id)
    }
}