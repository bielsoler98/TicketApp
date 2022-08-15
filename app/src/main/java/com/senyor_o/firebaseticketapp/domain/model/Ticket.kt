package com.senyor_o.firebaseticketapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senyor_o.firebaseticketapp.ui.theme.*

@Entity
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val description: String,
    val category: String,
    @ColumnInfo(name = "created_on") val createdOn: Long,
    @ColumnInfo(name = "opened_on") val openedOn: Long? = null,
    @ColumnInfo(name = "closed_on") val closedOn: Long? = null,
    val cardColor: CardColor
) {

    fun getType(): TicketType = if(openedOn == null) {
        TicketType.TODO
    } else if (closedOn == null) {
        TicketType.OPEN
    } else {
        TicketType.CLOSED
    }
}
