package com.senyor_o.firebaseticketapp.domain.model


sealed class TicketType(
    val id: Int,
    val title: String,
) {

    object ToDoTickets: TicketType( 0,"To Do Tickets")

    object ClosedTickets: TicketType(1,"Closed Tickets")

    object OpenTickets: TicketType(2,"Open Tickets")

}