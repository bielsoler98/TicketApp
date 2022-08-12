package com.senyor_o.firebaseticketapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.senyor_o.firebaseticketapp.domain.model.DrawerItem
import com.senyor_o.firebaseticketapp.ui.theme.AquaBlue
import com.senyor_o.firebaseticketapp.ui.theme.ButtonBlue
import com.senyor_o.firebaseticketapp.ui.theme.DeepBlue

@Composable
fun Drawer(
    items: List<DrawerItem>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 1,
    onItemClick: (DrawerItem) -> Unit
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, it ->
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    DrawerItem(
                        item = it,
                        isSelected = index == selectedItemIndex,
                        activeHighlightColor = activeHighlightColor,
                        activeTextColor = activeTextColor,
                        inactiveTextColor = inactiveTextColor
                    ) {
                        onItemClick(it)
                        selectedItemIndex = index
                    }
                }

            }
        }
    }
}

@Composable
fun DrawerItem(
    item: DrawerItem,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected) activeHighlightColor else Color.Transparent)
            .clickable {
                onItemClick()
            }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (isSelected) activeTextColor else inactiveTextColor,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }

}