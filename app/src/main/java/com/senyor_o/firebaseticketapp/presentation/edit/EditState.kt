package com.senyor_o.firebaseticketapp.presentation.edit

import com.senyor_o.firebaseticketapp.domain.model.Ticket

data class EditState(
    val ticket: Ticket? = null,
)