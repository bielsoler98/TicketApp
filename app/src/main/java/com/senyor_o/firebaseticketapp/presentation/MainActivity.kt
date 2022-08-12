package com.senyor_o.firebaseticketapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import com.senyor_o.firebaseticketapp.domain.repository.TicketRepository
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListViewModel
import com.senyor_o.firebaseticketapp.ui.theme.FirebaseTicketAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider {
        fun ticketListViewModelFactory(): TicketListViewModel.Factory
    }

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseTicketAppTheme {
                Navigation()
            }
        }
    }
}