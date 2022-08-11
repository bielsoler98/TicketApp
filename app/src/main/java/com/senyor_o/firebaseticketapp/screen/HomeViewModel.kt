package com.senyor_o.firebaseticketapp.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    val state: MutableState<HomeState> = mutableStateOf(HomeState())
}