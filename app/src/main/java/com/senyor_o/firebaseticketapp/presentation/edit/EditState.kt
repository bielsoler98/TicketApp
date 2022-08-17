package com.senyor_o.firebaseticketapp.presentation.edit

import com.senyor_o.firebaseticketapp.domain.model.CardColor
import com.senyor_o.firebaseticketapp.domain.model.Ticket

data class EditState(
    val ticket: Ticket? = null,
    var color: CardColor = CardColor.BLUE,
    val errorMessage: String? = null,
    val title: String = "",
    val category: String = "",
    val description: String = "",
    val creationDate: String = "",
    val openDate: String = "",
    val closeDate: String = "",
)