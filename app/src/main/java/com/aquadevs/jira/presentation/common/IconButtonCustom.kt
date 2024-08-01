package com.aquadevs.jira.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aquadevs.jira.R
import com.aquadevs.jira.ui.validateTheme

@Composable
fun IconButtonCustom(
    modifier: Modifier = Modifier,
    modifierIcon: Modifier = Modifier,
    icon: ImageVector? = null,
    @DrawableRes iconDR: Int? = null,
    iconColor: Color = validateTheme().onPrimary,
    iconSize: Int = 14,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(iconSize.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(iconSize.dp)
            )
        }

        if (iconDR != null) {
            Icon(
                painter = painterResource(id = iconDR),
                contentDescription = null,
                tint = iconColor,
                modifier = modifierIcon.size(iconSize.dp)
            )
        }

    }
}

@Composable
fun IconCustom(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconDR: Int? = null,
    iconColor: Color = validateTheme().onPrimary,
    iconSize: Int = 14
) {
    if (icon != null){
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = modifier.size(iconSize.dp)
        )
    }

    if (iconDR != null){
        Icon(
            painter = painterResource(id = iconDR),
            contentDescription = null,
            tint = iconColor,
            modifier = modifier.size(iconSize.dp)
        )
    }
}