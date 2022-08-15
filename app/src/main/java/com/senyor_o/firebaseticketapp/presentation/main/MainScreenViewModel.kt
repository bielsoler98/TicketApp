package com.senyor_o.firebaseticketapp.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.TicketListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

): ViewModel() {

    private val _state: MutableState<DrawerState> = mutableStateOf(DrawerState())
    val state: State<DrawerState> = _state

    fun changeIndex(idx: Int) {
        _state.value = state.value.copy(
            selectedIndex = idx
        )
    }
}