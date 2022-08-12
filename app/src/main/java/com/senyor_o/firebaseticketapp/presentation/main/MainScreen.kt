package com.senyor_o.firebaseticketapp.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senyor_o.firebaseticketapp.domain.model.DrawerItem
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import com.senyor_o.firebaseticketapp.presentation.components.Drawer
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListScreen
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TopBar
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalFoundationApi
@Composable
fun MainScreen(
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.background(DeepBlue),
        topBar = {
            TopBar(
                onNavigationClicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerBackgroundColor = DeepBlue,
        drawerContent = {
            Drawer(
                items = listOf(
                    DrawerItem("Home", Icons.Default.Home, Screen.Home.route),
                    DrawerItem("To Do", Icons.Default.ListAlt, Screen.ToDoTickets.route),
                    DrawerItem("Open", Icons.Default.Schedule, Screen.OpenTickets.route),
                    DrawerItem("Completed", Icons.Default.TaskAlt, Screen.ClosedTickets.route)
                ),
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        floatingActionButton = {

        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.OpenTickets.route
        ) {
            composable(route = Screen.OpenTickets.route) {
                TicketListScreen(navController = navController, TicketType.OpenTickets)
            }
            composable(route = Screen.ClosedTickets.route) {
                TicketListScreen(navController = navController, TicketType.ClosedTickets)
            }
            composable(route = Screen.ToDoTickets.route) {
                TicketListScreen(navController = navController, TicketType.ToDoTickets)
            }
        }
    }
}
