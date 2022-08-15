package com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senyor_o.firebaseticketapp.ui.theme.TextWhite

@Composable
fun TicketStateButton(
    text: String,
    modifier: Modifier = Modifier,
    colorText: Color = TextWhite,
    backgroundColor: Color,
    onClick: () -> Unit = {}
) {
    Text(
        text = text,
        color = colorText,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(vertical = 6.dp, horizontal = 15.dp)
    )
}