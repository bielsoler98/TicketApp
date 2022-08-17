package com.senyor_o.firebaseticketapp.presentation.edit.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue

@Composable
fun EventDialog(
    modifier: Modifier = Modifier,
    errorMessage: String ,
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog(
        modifier = modifier
            .background(Color.Transparent)
            .padding(16.dp),
        onDismissRequest = { onDismiss?.invoke() },
        title = {
            Text(
                "Error",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        text = {
            Text(
                text = errorMessage,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { onDismiss?.invoke() }) {
                    Text(text = "Accept")
                }
            }
        },
        backgroundColor = DeepBlue
    )
}

@Preview
@Composable
fun EventDialogPreview() {
    EventDialog(
        errorMessage =  "Error",
        onDismiss = {}
    )
}