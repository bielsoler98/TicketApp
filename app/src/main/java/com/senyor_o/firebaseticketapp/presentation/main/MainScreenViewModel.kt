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

    private val _drawerState: MutableState<DrawerState> = mutableStateOf(DrawerState())
    val drawerState: State<DrawerState> = _drawerState

    fun changeIndex(idx: Int) {
        _drawerState.value = drawerState.value.copy(
            selectedIndex = idx
        )
    }
}