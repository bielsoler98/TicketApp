package com.senyor_o.firebaseticketapp.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senyor_o.firebaseticketapp.domain.model.DrawerItem
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.presentation.AppScreen
import com.senyor_o.firebaseticketapp.presentation.main.components.Drawer
import com.senyor_o.firebaseticketapp.presentation.main.components.MainFab
import com.senyor_o.firebaseticketapp.presentation.main.home.HomeScreen
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListScreen
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TopBar
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    parentNavController: NavController
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val drawerState = viewModel.state.value

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
                    DrawerItem("Home", Icons.Default.Home) {
                        navController.navigate(Screen.Home.route) {
                            navController.popBackStack()
                        }
                    },
                    DrawerItem("To Do", Icons.Default.ListAlt){
                        navController.navigate(
                            route = Screen.TicketList.passId(TicketType.TODO.ordinal)
                        ) {
                            navController.popBackStack()
                        }
                    },
                    DrawerItem("Open", Icons.Default.Schedule){
                        navController.navigate(
                            route = Screen.TicketList.passId(TicketType.OPEN.ordinal)
                        ) {
                            navController.popBackStack()
                        }
                    },
                    DrawerItem("Completed", Icons.Default.TaskAlt){
                        navController.navigate(
                            route = Screen.TicketList.passId(TicketType.CLOSED.ordinal)
                        ) {
                            navController.popBackStack()
                        }
                    }
                ),
                selectedIndex = drawerState.selectedIndex
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        floatingActionButton = {
            MainFab(
                onFabClicked = {
                    parentNavController.navigate(AppScreen.EditScreen.route)
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(
                route = Screen.TicketList.route,
                arguments = listOf(
                    navArgument(
                        name = "ticketTypeId"
                    ) {
                        type = NavType.IntType
                    },
                    navArgument(
                        name = "scrollTo"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    }
                )
            ) {
                val idx = it.arguments?.getInt("ticketTypeId") ?: 0
                viewModel.changeIndex(idx)
                TicketListScreen(
                    navController = parentNavController,
                    scaffoldState = scaffoldState
                )
            }
            composable(
                route = Screen.Home.route,
            ) {
                viewModel.changeIndex(0)
                HomeScreen(navController)
            }
        }
    }
}
