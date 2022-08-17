package com.senyor_o.firebaseticketapp.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

): ViewModel() {

    private val _state: MutableState<MainScreenState> = mutableStateOf(MainScreenState())
    val state: State<MainScreenState> = _state

    fun changeIndex(idx: Int) {
        _state.value = _state.value.copy(
            selectedIndex = idx
        )
    }
}