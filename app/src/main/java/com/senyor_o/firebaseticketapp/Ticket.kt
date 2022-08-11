package com.senyor_o.firebaseticketapp

import androidx.compose.ui.graphics.Color

data class Ticket(
    val title: String,
    val description: String,
    val category: String,
    val createdOn: Long,
    val openedOn: Long?,
    val closedOn: Long?,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
