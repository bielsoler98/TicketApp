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
    fun getCardColors()  =
        when (cardColor) {
            CardColor.RED -> CardColorSet(OrangeYellow1, OrangeYellow2, OrangeYellow3)
            CardColor.GREEN -> CardColorSet(LightGreen1, LightGreen2, LightGreen3)
            CardColor.BLUE -> CardColorSet(BlueViolet1, BlueViolet2, BlueViolet3)
            CardColor.YELLOW -> CardColorSet(Beige1, Beige2, Beige3)
        }

    fun isPending() = openedOn == null

    fun isOpen() = openedOn != null && closedOn == null

    fun isClosed() = closedOn != null
}
