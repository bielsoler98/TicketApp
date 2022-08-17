package com.senyor_o.firebaseticketapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.senyor_o.firebaseticketapp.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

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

    fun getFormattedCreationDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return formatter.format(Date(createdOn))
    }

    fun getFormattedOpenDate(): String {
        openedOn?.let {
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return formatter.format(Date(openedOn))
        }
        return ""
    }

    fun getFormattedClosedDate(): String {
        closedOn?.let {
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            return formatter.format(Date(closedOn))
        }
        return ""
    }
}
