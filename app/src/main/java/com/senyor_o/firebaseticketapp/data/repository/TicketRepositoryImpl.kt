package com.senyor_o.firebaseticketapp.data.repository

import com.senyor_o.firebaseticketapp.data.room.dao.TicketDao
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepositoryImpl @Inject  constructor(
    private val dao: TicketDao,
): TicketRepository {
    override fun getAllTickets(): Flow<List<Ticket>> {
        return dao.getAllTickets()
    }

    override fun getAllToDoTickets(): Flow<List<Ticket>> {
        return dao.getAllToDoTickets()
    }

    override fun getAllOpenTickets(): Flow<List<Ticket>> {
        return dao.getAllOpenTickets()
    }

    override fun getAllClosedTickets(): Flow<List<Ticket>> {
        return dao.getAllClosedTickets()
    }

    override suspend fun getTicketById(id: Int): Ticket {
        return dao.getTicketById(id)
    }

    override suspend fun insertTicket(ticket: Ticket) {
        dao.insertTicket(ticket)
    }

    override suspend fun deleteTicket(ticket: Ticket) {
        dao.deleteTicket(ticket)
    }

}