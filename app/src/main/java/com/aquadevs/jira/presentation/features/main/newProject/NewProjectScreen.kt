package com.aquadevs.jira.presentation.features.main.newProject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.common.ButtonCustom
import com.aquadevs.jira.presentation.common.IconButtonCustom
import com.aquadevs.jira.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.jira.presentation.common.TextCustom
import com.aquadevs.jira.ui.validateTheme

@Composable
fun NewProjectScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        MyHeader(navController = navController)
        MyBody()
        MyFooter()
    }
}

@Composable
fun MyFooter(newProjectViewModel: NewProjectViewModel = hiltViewModel()) {
    ButtonCustom(
        textButton = stringResource(id = R.string.createProject),
        modifierText = Modifier.padding(vertical = 5.dp),
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
        fontSize = 16
    ) {

    }
}

@Composable
fun MyBody(newProjectViewModel: NewProjectViewModel = hiltViewModel()) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.projectName),
            modifier = Modifier.fillMaxWidth()
        ) {

        }

        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.description),
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
        ) {

        }

        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.projectState),
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
            trailingIcon = R.drawable.icon_chevron_right
        ) {

        }
        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.startDate),
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
            trailingIcon = R.drawable.icon_calendar_today
        ) {

        }

        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.endDate),
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
            trailingIcon = R.drawable.icon_calendar_today
        ) {

        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextCustom(
                text = stringResource(id = R.string.shareWithOtherMembers),
                color = validateTheme().secondary,
                modifier = Modifier.weight(1f)
            )
            Switch(checked = false, onCheckedChange = {})
        }
    }
}

@Composable
private fun MyHeader(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxWidth()) {
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
        Spacer(modifier = Modifier.height(20.dp))
        TextCustom(text = stringResource(id = R.string.projectIcon))
        ChaneIcon()
    }
}

@Composable
private fun ChaneIcon(newProjectViewModel: NewProjectViewModel = hiltViewModel()) {
    val icon by newProjectViewModel.randomIcon.observeAsState(initial = R.drawable.icon_cloud_computing)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12))
                .background(validateTheme().background)
                .border(1.dp, validateTheme().secondary, RoundedCornerShape(12))
                .size(54.dp),
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        TextCustom(
            text = stringResource(id = R.string.changeRandomIcon),
            color = validateTheme().secondary,
            modifier = Modifier.padding(end = 5.dp)
        )
        IconButtonCustom(
            iconDR = R.drawable.icon_autorenew,
            iconSize = 28,
            iconColor = validateTheme().primary
        ) {
            newProjectViewModel.generateRandomIcon()
        }
        Spacer(modifier = Modifier.width(10.dp))
    }
}
