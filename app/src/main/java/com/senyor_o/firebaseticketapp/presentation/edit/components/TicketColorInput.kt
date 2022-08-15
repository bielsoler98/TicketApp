package com.senyor_o.firebaseticketapp.presentation.edit.components

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.senyor_o.firebaseticketapp.domain.model.CardColor
import com.senyor_o.firebaseticketapp.presentation.edit.ColorState
import com.senyor_o.firebaseticketapp.presentation.edit.EditEvent
import com.senyor_o.firebaseticketapp.presentation.edit.EditViewModel

@Composable
fun TicketColorInput(
    state: State<ColorState>,
    color: CardColor,
    onClick: (EditEvent) -> Unit
) {
    IconToggleButton(
        checked = state.value.color == color,
        onCheckedChange = {
            onClick(EditEvent.EnteredColor(color))
        }
    ) {
        Icon(
            imageVector = if (state.value.color == color) Icons.Filled.CheckCircle else Icons.Default.RadioButtonUnchecked,
            contentDescription = "Radio button icon",
            tint = color.cardColorSet.lightColor
        )
    }
}

@Preview
@Composable
fun PreviewRoundPicker() {
    val viewModel: EditViewModel = hiltViewModel()
    TicketColorInput(viewModel.ticketColor, CardColor.RED, onClick = {})
}