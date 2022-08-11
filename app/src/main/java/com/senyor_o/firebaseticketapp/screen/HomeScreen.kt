package com.senyor_o.firebaseticketapp.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import com.senyor_o.firebaseticketapp.DrawerItem
import com.senyor_o.firebaseticketapp.components.Drawer
import com.senyor_o.firebaseticketapp.components.TicketSection
import com.senyor_o.firebaseticketapp.components.TopBar
import com.senyor_o.firebaseticketapp.ui.theme.*
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val viewModel = HomeViewModel()
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
                    DrawerItem("Home", Icons.Default.Home),
                    DrawerItem("To Do", Icons.Default.ListAlt),
                    DrawerItem("Open", Icons.Default.Schedule),
                    DrawerItem("Completed", Icons.Default.TaskAlt)
                ),
                onItemClick = {
                    println("Clicked on ${it.title}")
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(DeepBlue)
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                TicketSection(
                    title = "Open Tickets",
                    tickets = viewModel.state.value.tickets
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirebaseTicketAppTheme {
        HomeScreen()
    }
}