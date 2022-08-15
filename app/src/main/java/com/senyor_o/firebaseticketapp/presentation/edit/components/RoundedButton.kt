package com.senyor_o.firebaseticketapp.presentation.edit.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.senyor_o.firebaseticketapp.ui.theme.Beige1

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.width(280.dp).height(50.dp),
        onClick = onClick,
        shape = RoundedCornerShape(50),
    ) {
        Text(
            text = text
        )
    }
}