package com.senyor_o.firebaseticketapp.screen

import com.senyor_o.firebaseticketapp.Ticket
import com.senyor_o.firebaseticketapp.ui.theme.*

data class HomeState(
    val tickets: MutableList<Ticket> = listOf(
        Ticket(
            title = "Sleep meditation",
            "To Do 1",
            "To Do 1",
            0,
            null,
            null,
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
        ),
        Ticket(
            title = "Tips for sleeping",
            "This is a description",
            "To Do 2",
            0,
            null,
            null,
            LightGreen1,
            LightGreen2,
            LightGreen3
        ),
        Ticket(
            title = "Night island",
            "This is a description",
            "To Do 3",
            0,
            null,
            null,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        Ticket(
            title = "Calming sounds",
            "This is a description",
            "To Do 4",
            0,
            null,
            null,
            Beige1,
            Beige2,
            Beige3
        ),
        Ticket(
            title = "Sleep meditation",
            "Open 1",
            "Open 1",
            0,
            System.currentTimeMillis(),
            null,
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
        ),
        Ticket(
            title = "Tips for sleeping",
            "This is a description",
            "Open 2",
            0,
            System.currentTimeMillis(),
            null,
            LightGreen1,
            LightGreen2,
            LightGreen3
        ),
        Ticket(
            title = "Night island",
            "This is a description",
            "Open 3",
            0,
            System.currentTimeMillis(),
            null,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        Ticket(
            title = "Calming sounds",
            "This is a description",
            "Open 4",
            0,
            System.currentTimeMillis(),
            null,
            Beige1,
            Beige2,
            Beige3
        ),
        Ticket(
            title = "Calming sounds",
            "This is a description",
            "To Do 4",
            0,
            null,
            null,
            Beige1,
            Beige2,
            Beige3
        ),
        Ticket(
            title = "Sleep meditation",
            "Close 1",
            "Biel Soler",
            0,
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
        ),
        Ticket(
            title = "Tips for sleeping",
            "This is a description",
            "Close 2",
            0,
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            LightGreen1,
            LightGreen2,
            LightGreen3
        ),
        Ticket(
            title = "Night island",
            "This is a description",
            "Close 3",
            0,
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        Ticket(
            title = "Calming sounds",
            "This is a description",
            "Close 4",
            0,
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            Beige1,
            Beige2,
            Beige3
        )
    ).toMutableList(),
    val tabSelected: Int = 0
)