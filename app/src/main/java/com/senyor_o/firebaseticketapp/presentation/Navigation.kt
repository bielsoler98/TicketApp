package com.senyor_o.firebaseticketapp.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senyor_o.firebaseticketapp.presentation.edit.EditScreen
import com.senyor_o.firebaseticketapp.presentation.main.MainScreen

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun Navigation(
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.MainScreen.route
    ) {
        composable(route = AppScreen.MainScreen.route) {
            MainScreen(parentNavController = navController)
        }
        composable(
            route = AppScreen.EditScreen.route,
            arguments = listOf(
                navArgument(
                    name = "ticketId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            EditScreen(navController)
        }
    }
}