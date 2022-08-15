package com.senyor_o.firebaseticketapp.presentation.edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.senyor_o.firebaseticketapp.presentation.edit.EditEvent

@Composable
fun TicketInputText(
    title: String,
    text: String,
    maxChar: Int,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.length <= maxChar) onTextChange(it)
            },
            singleLine = singleLine,
            maxLines = maxLines,
            label = { Text(title) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text(
            text = "${text.length} / $maxChar",
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth().padding(end = 32.dp)
        )
    }
}