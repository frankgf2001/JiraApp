package com.aquadevs.jira.presentation.features.main.profile

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aquadevs.jira.R
import com.aquadevs.jira.core.function.General
import com.aquadevs.jira.presentation.common.ButtonCustom
import com.aquadevs.jira.presentation.common.IconButtonCustom
import com.aquadevs.jira.presentation.common.ImageAsyncCustom
import com.aquadevs.jira.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.jira.presentation.common.TextCustom
import com.aquadevs.jira.presentation.model.PersonDto
import com.aquadevs.jira.presentation.navigation.MainRoute
import com.aquadevs.jira.ui.validateTheme

@Composable
fun ProfileScreen(
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
        MyFooter(navController = navController)
        MyDialogCustom()
    }
}

@Composable
fun MyFooter(navController: NavController) {
    ButtonCustom(
        textButton = stringResource(id = R.string.save),
        modifierText = Modifier.padding(vertical = 6.dp),
        modifier = Modifier
            .padding(top = 15.dp)
            .fillMaxWidth(),
        fontSize = 16
    ) {
        navController.popBackStack()
    }
}

@Composable
private fun MyBody(profileViewModel: ProfileViewModel = hiltViewModel()) {
    val personDto by profileViewModel.personDto.observeAsState(initial = PersonDto())

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextFieldCustom(
            value = personDto.userName,
            label = stringResource(id = R.string.names),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            profileViewModel.validateProfile(names = it)
        }

        OutlinedTextFieldCustom(
            value = personDto.userSurname,
            label = stringResource(id = R.string.surnames),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            profileViewModel.validateProfile(surnames = it)
        }

        OutlinedTextFieldCustom(
            value = personDto.companyName,
            label = stringResource(id = R.string.companyName),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            profileViewModel.validateProfile(companyName = it)
        }

        OutlinedTextFieldCustom(
            value = personDto.positionCompany,
            label = stringResource(id = R.string.positionCompany),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            profileViewModel.validateProfile(positionCompany = it)
        }

        OutlinedTextFieldCustom(
            value = personDto.cellPhone,
            label = stringResource(id = R.string.cellPhone),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            profileViewModel.validateProfile(cellPhone = it)
        }

        OutlinedTextFieldCustom(
            value = personDto.email,
            label = stringResource(id = R.string.email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            profileViewModel.validateProfile(email = it)
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
        MyProfilePicture(navController = navController)
    }
}

@Composable
private fun MyProfilePicture(navController: NavController) {
    Box {
        ImageAsyncCustom(
            urlPicture = "https://s3-alpha-sig.figma.com/img/af22/e37f/c59a84890081e9133704fdb55b8401e3?Expires=1723420800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=lKGdBelAq2GU04i92kHOk1KyeohtpJi1ldk1oOzFvgorevwMdAc5f8H1nT5ssOpg9~~O4t205HKumg10Pb-Ed4PNdZdaH7r-HjBt3P7e7QSra3yl21XsbrInoWHLZ5AAjv2UtUioOavYIW3AMJdhlSZZD39Qcg8bG2s4McOh-Z0462jcvlvoB0~4Exm3yj6rZydJgOW1N0hh0rC1v-BrtQurdbvejFw2i-AsrvsfPsjKkZige2Z~g7c~Ixf6IpzTIJ4vDkrenFeeN58JWe3s4X4fsCt-TujINy-k5p-W-6Ze5ZSD7of0gnnRX96VoYQ24ho38upzfAEVvMtDcSKWMg__",
            modifier = Modifier
                .size(83.dp)
                .clip(CircleShape)
        )
        IconButtonCustom(
            iconDR = R.drawable.icon_edit,
            iconColor = validateTheme().primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .background(validateTheme().background)
                .border(1.dp, validateTheme().secondary, CircleShape),
            modifierIcon = Modifier.padding(6.dp),
            iconSize = 32
        ) {
            navController.navigate(MainRoute.NavEditPhotoScreen.route)
        }
    }
}
@Composable
private fun MyDialogCustom(profileViewModel: ProfileViewModel = hiltViewModel()) {
    val isLoading by profileViewModel.isLoading.observeAsState(initial = false)
    if (isLoading) General.DialogProgress()
}