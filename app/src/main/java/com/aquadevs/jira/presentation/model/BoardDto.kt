package com.aquadevs.jira.presentation.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class BoardDto(
    val idBoard: Int = 0,
    @DrawableRes val icon: Int = 0,
    val nameItem:String = "",
    val codItem:String = "",
    val stateCode:String = "",
    val stateDescription:String = "",
    val colorState:Color = Color.Transparent
)
