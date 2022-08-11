package com.senyor_o.firebaseticketapp.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senyor_o.firebaseticketapp.BottomMenuContent
import com.senyor_o.firebaseticketapp.R
import com.senyor_o.firebaseticketapp.Ticket
import com.senyor_o.firebaseticketapp.components.BottomBar
import com.senyor_o.firebaseticketapp.components.TicketSection
import com.senyor_o.firebaseticketapp.components.TopBar
import com.senyor_o.firebaseticketapp.ui.theme.*

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.background(DeepBlue),
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(items = listOf(
                BottomMenuContent("Open", R.drawable.ic_home),
                BottomMenuContent("Closed", R.drawable.ic_bubble)
            )
            )
        }
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
                    tickets = listOf(
                        Ticket(
                            title = "Sleep meditation",
                            "This is a description",
                            "Biel Soler",
                            0,
                            null,
                            R.drawable.ic_headphone,
                            BlueViolet1,
                            BlueViolet2,
                            BlueViolet3
                        ),
                        Ticket(
                            title = "Tips for sleeping",
                            "This is a description",
                            "Biel Soler",
                            0,
                            null,
                            R.drawable.ic_videocam,
                            LightGreen1,
                            LightGreen2,
                            LightGreen3
                        ),
                        Ticket(
                            title = "Night island",
                            "This is a description",
                            "Biel Soler",
                            0,
                            null,
                            R.drawable.ic_headphone,

                            OrangeYellow1,
                            OrangeYellow2,
                            OrangeYellow3
                        ),
                        Ticket(
                            title = "Calming sounds",
                            "This is a description",
                            "Biel Soler",
                            0,
                            null,
                            R.drawable.ic_headphone,
                            Beige1,
                            Beige2,
                            Beige3
                        )
                    )
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