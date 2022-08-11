package com.senyor_o.firebaseticketapp

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Ticket(
    val title: String,
    val description: String,
    val sentBy: String,
    val createdOn: Long,
    val closedOn: Long?,
    @DrawableRes val iconId: Int,
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
)
