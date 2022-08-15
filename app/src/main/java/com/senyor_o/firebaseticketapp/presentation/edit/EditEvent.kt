package com.senyor_o.firebaseticketapp.presentation.edit

import com.senyor_o.firebaseticketapp.domain.model.CardColor

sealed class EditEvent {
    
    data class EnteredTitle(val value: String): EditEvent()

    data class EnteredDescription(val value: String): EditEvent()

    data class EnteredCategory(val value: String): EditEvent()

    data class EnteredColor(val value: CardColor): EditEvent()

    object InsertTicket: EditEvent()
}
