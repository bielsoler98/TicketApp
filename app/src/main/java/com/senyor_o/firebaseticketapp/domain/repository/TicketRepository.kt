package com.senyor_o.firebaseticketapp.domain.repository

import com.senyor_o.firebaseticketapp.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketRepository {

    fun getAllTickets(): Flow<List<Ticket>>

    fun getAllToDoTickets(): Flow<List<Ticket>>

    fun getAllOpenTickets(): Flow<List<Ticket>>

    fun getAllClosedTickets(): Flow<List<Ticket>>

    suspend fun getTicketById(id: Int): Ticket

    suspend fun insertTicket(ticket: Ticket)

    suspend fun deleteTicket(ticket: Ticket)
}