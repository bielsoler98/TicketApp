package com.senyor_o.firebaseticketapp.presentation.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senyor_o.firebaseticketapp.domain.model.Ticket
import com.senyor_o.firebaseticketapp.presentation.components.TicketCard
import com.senyor_o.firebaseticketapp.presentation.main.ticket_list.components.TicketStateButton
import com.senyor_o.firebaseticketapp.ui.theme.DarkerButtonBlue
import com.senyor_o.firebaseticketapp.ui.theme.LightGreen1
import com.senyor_o.firebaseticketapp.ui.theme.LightRed
import com.senyor_o.firebaseticketapp.ui.theme.TextWhite

@ExperimentalFoundationApi
@Composable
fun TicketSection(
    title: String,
    tickets: List<Ticket>,
    onItemClicked: (Int?) -> Unit,
    startButton: @Composable (Modifier, Ticket) -> Unit = {_, _ -> },
    endButton: @Composable (Modifier, Ticket) -> Unit = {_, _ -> },
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
                        title = it.title,
                        category = it.category,
                        cardColor = it.cardColor.cardColorSet,
                        onClick = {
                            onItemClicked(it.id)
                        },
                        startButton = { modifier ->
                            startButton(modifier, it)
                        },
                        endButton = { modifier ->
                            endButton(modifier, it)
                        }
                    )
                }
            }
        }
    }
}