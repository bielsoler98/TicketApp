package com.senyor_o.firebaseticketapp.data.room.dao

import androidx.room.*
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {

    @Query("SELECT * FROM  Ticket")
    fun getAllTickets(): Flow<List<Ticket>>

    @Query("SELECT * FROM  Ticket WHERE opened_on is null")
    fun getAllToDoTickets(): Flow<List<Ticket>>

    @Query("SELECT * FROM  Ticket WHERE closed_on is null AND opened_on is not null")
    fun getAllOpenTickets(): Flow<List<Ticket>>

    @Query("SELECT * FROM  Ticket WHERE closed_on is not null")
    fun getAllClosedTickets(): Flow<List<Ticket>>

    @Query("SELECT * FROM  Ticket WHERE id = :id")
    suspend fun getTicketById(id: Int): Ticket

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: Ticket)

    @Delete
    suspend fun deleteTicket(ticket: Ticket)
}