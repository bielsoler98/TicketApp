package com.senyor_o.firebaseticketapp.presentation.edit.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.senyor_o.firebaseticketapp.R
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue

@Composable
fun EditTopBar(
    onNavigationClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        backgroundColor = DeepBlue,
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = { onNavigationClicked() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "go back"
                )
            }
        }
    )
}