package com.senyor_o.firebaseticketapp.presentation.main.ticket_list

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.senyor_o.firebaseticketapp.domain.model.TicketType
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import com.senyor_o.firebaseticketapp.presentation.MainActivity
import com.senyor_o.firebaseticketapp.presentation.components.TicketSection
import com.senyor_o.firebaseticketapp.ui.theme.*
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@ExperimentalFoundationApi
@Composable
fun TicketListScreen(
    navController: NavController,
    ticketType: TicketType,
) {
    val viewModelFactory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).ticketListViewModelFactory()

    val itemViewModel: TicketListViewModel = viewModel(
        factory = TicketListViewModel.provideFactory(
            viewModelFactory,
            ticketType
        )
    )
    val state = itemViewModel.state.value
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            TicketSection(
                title = ticketType.title,
                tickets = state.tickets
            )
        }
    }
}