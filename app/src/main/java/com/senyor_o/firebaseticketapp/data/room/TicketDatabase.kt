package com.senyor_o.firebaseticketapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.senyor_o.firebaseticketapp.data.room.dao.TicketDao
import com.senyor_o.firebaseticketapp.domain.model.Ticket

@Database(
    entities = [Ticket::class],
    version = 1,
    exportSchema = false
)
abstract class TicketDatabase: RoomDatabase() {
    abstract val ticketDao: TicketDao
}