package com.senyor_o.firebaseticketapp.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.senyor_o.firebaseticketapp.domain.model.Ticket

@ExperimentalFoundationApi
@Composable
fun TicketSection(
    title: String,
    tickets: List<Ticket>
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyColumn(
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            tickets.forEach {
                item {
                    TicketCard(
                        ticket = it,
                        cardColor = it.getCardColors()
                    )
                }
            }
        }
    }
}