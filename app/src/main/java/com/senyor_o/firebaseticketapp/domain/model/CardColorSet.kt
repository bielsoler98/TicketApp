package com.senyor_o.firebaseticketapp.domain.model

import androidx.compose.ui.graphics.Color
import com.senyor_o.firebaseticketapp.ui.theme.*

sealed class CardColorSet(
    val lightColor: Color,
    val mediumColor: Color,
    val darkColor: Color
) {
    object RED : CardColorSet(OrangeYellow1, OrangeYellow2, OrangeYellow3)
    object GREEN : CardColorSet(LightGreen1, LightGreen2, LightGreen3)
    object BLUE : CardColorSet(BlueViolet1, BlueViolet2, BlueViolet3)
    object YELLOW : CardColorSet(Beige1, Beige2, Beige3)
}