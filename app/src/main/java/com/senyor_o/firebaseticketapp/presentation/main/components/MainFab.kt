package com.senyor_o.firebaseticketapp.presentation.main.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senyor_o.firebaseticketapp.ui.theme.BlueViolet2

@Composable
fun MainFab(
    modifier: Modifier = Modifier,
    onFabClicked: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = onFabClicked,
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        backgroundColor = BlueViolet2
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add fabButton"
        )
    }
}