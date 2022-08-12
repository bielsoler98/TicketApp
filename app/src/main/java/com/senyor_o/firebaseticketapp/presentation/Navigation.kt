package com.senyor_o.firebaseticketapp.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import com.senyor_o.firebaseticketapp.presentation.edit.EditScreen
import com.senyor_o.firebaseticketapp.presentation.main.MainScreen

@ExperimentalFoundationApi
@Composable
fun Navigation(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen()
        }
//        composable(route = Screen.EditScreen.route) {
//            EditScreen(navController)
//        }
    }

}