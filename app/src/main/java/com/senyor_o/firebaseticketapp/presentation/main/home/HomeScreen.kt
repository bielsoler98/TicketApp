package com.senyor_o.firebaseticketapp.presentation.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Home"
            )
        }
    }
}