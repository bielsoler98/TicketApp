package com.senyor_o.firebaseticketapp.domain.model


enum class TicketType(val title: String) {
    ALL("All"), TODO("To Do Tickets"), OPEN("Open Tickets"), CLOSED("Completed Tickets")
}