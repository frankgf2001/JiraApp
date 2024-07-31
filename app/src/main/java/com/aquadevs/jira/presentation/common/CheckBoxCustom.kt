package com.aquadevs.jira.presentation.common

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aquadevs.jira.ui.validateTheme

@Composable
fun CheckBoxCustom(
    checked:Boolean,
    enabled:Boolean,
    onCheckChange:(Boolean) -> Unit
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckChange,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = validateTheme().primary,
            checkmarkColor = Color.White,
            disabledCheckedColor = validateTheme().onPrimary.copy(alpha = 0.6f),
            uncheckedColor = validateTheme().onPrimary
        )
    )
}