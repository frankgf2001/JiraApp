package com.aquadevs.jira.presentation.features.main.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.common.IconButtonCustom
import com.aquadevs.jira.presentation.common.TextCustom
import com.aquadevs.jira.ui.validateTheme

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MyHeader(navController = navController)
    }
}
@Composable
private fun MyHeader(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonCustom(
            iconDR = R.drawable.icon_arrow_back,
            iconColor = validateTheme().primary,
            iconSize = 26
        ) {
            navController.popBackStack()
        }
        TextCustom(
            text = stringResource(id = R.string.newProject),
            fontSize = 22,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}