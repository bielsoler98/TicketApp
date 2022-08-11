package com.senyor_o.firebaseticketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import com.senyor_o.firebaseticketapp.screen.HomeScreen
import com.senyor_o.firebaseticketapp.ui.theme.FirebaseTicketAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseTicketAppTheme {
                HomeScreen()
            }
        }
    }
}