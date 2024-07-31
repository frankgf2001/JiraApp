package com.aquadevs.jira.presentation.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aquadevs.jira.ui.validateTheme
@Composable
fun CircularProgressCustom(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = validateTheme().primary
    )
}