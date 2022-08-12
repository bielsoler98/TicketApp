package com.senyor_o.firebaseticketapp.presentation.edit

import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.senyor_o.firebaseticketapp.presentation.edit.components.EditTopBar
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue

@Composable
fun EditScreen(
    navController: NavHostController
) {
    Scaffold(
        modifier = Modifier.background(DeepBlue),
        topBar = {
            EditTopBar(
                onNavigationClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) {

    }
}