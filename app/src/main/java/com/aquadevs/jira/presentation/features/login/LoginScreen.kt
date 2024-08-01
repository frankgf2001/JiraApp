package com.aquadevs.jira.presentation.features.login

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aquadevs.jira.R
import com.aquadevs.jira.core.function.General.DialogProgress
import com.aquadevs.jira.core.function.General.changeActivity
import com.aquadevs.jira.presentation.common.ButtonCustom
import com.aquadevs.jira.presentation.common.CheckBoxCustom
import com.aquadevs.jira.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.jira.presentation.common.TextCustom
import com.aquadevs.jira.presentation.common.ToastCustom
import com.aquadevs.jira.presentation.features.main.MainActivity
import com.aquadevs.jira.ui.validateTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        MyHeader()
        MyBody()
        MyFooter()
        MyDialogCustom()
    }
}

@Composable
private fun MyFooter(loginViewModel: LoginViewModel = hiltViewModel()) {
    val activity = LocalContext.current as Activity
    val isEnabledButton: Boolean by loginViewModel.enabledButton.observeAsState(initial = false)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 120.dp)
    ) {
        ButtonCustom(
            textButton = stringResource(id = R.string.logIn),
            modifier = Modifier
                .clip(RoundedCornerShape(15))
                .fillMaxWidth(),
            fontSize = 16,
            enabled = isEnabledButton
        ) {
            loginViewModel.signIn {
                activity.changeActivity(MainActivity())
            }
        }
    }
}

@Composable
private fun MyBody(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var isPass by remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity
    val user: String by loginViewModel.user.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoading: Boolean by loginViewModel.isLoading.observeAsState(initial = false)
    val isRememberPassword: Boolean by loginViewModel.isRememberPassword.observeAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp)
    ) {
        OutlinedTextFieldCustom(
            value = user,
            label = stringResource(id = R.string.email),
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            loginViewModel.validateLogin(context = context, user = it)
        }

        OutlinedTextFieldCustom(
            value = password,
            label = stringResource(id = R.string.password),
            modifier = Modifier.fillMaxWidth(),
            keyBoardType = KeyboardType.Password,
            visualTransformation = if (isPass) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = if (isPass) R.drawable.visibility_48dp else R.drawable.visibility_off_48dp,
            sizeIcon = 25,
            enabled = !isLoading,
            onClickTrailingIcon = {
                isPass = !isPass
            }
        ) {
            loginViewModel.validateLogin(context = context, password = it)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            RememberPassword(checked = isRememberPassword, enabled = !isLoading) {
                loginViewModel.validateLogin(context = context, bool = it)
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(onClick = { }) {
                TextCustom(
                    text = stringResource(id = R.string.forgotPassword),
                    color = validateTheme().primary,
                    fontSize = 12
                )
            }
        }
    }
}

@Composable
private fun RememberPassword(
    checked: Boolean,
    enabled: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CheckBoxCustom(
            checked = checked,
            enabled = enabled,
        ) {
            onChecked(it)
        }
        TextCustom(
            text = stringResource(id = R.string.rememberMe),
            fontSize = 12
        )
    }
}

@Composable
private fun MyHeader(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.icon_jira),
            contentDescription = null,
            modifier = Modifier
                .size(116.dp, 90.dp)
                .padding(bottom = 10.dp)
        )
        TextCustom(
            text = "¡${stringResource(id = R.string.welcome)}!",
            fontWeight = FontWeight.Bold,
            fontSize = 22
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            TextCustom(
                text = "${stringResource(id = R.string.to)} ",
                fontWeight = FontWeight.Bold,
                fontSize = 22
            )
            TextCustom(
                text = stringResource(id = R.string.jiraAtllassian),
                fontWeight = FontWeight.Bold,
                color = validateTheme().primary,
                fontSize = 22
            )
            TextCustom(
                text = "!",
                fontWeight = FontWeight.Bold,
                fontSize = 22
            )
        }
        TextCustom(
            text = stringResource(id = R.string.helloPleaseLogInContinue),
            color = Color(0XFF8C8C8C),
            fontSize = 16,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
    loginViewModel.getDataStore(LocalContext.current)
}

@Composable
private fun MyDialogCustom(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val messageToast by loginViewModel.messageToast.observeAsState(initial = 0)
    val isLoading by loginViewModel.isLoading.observeAsState(initial = false)
    if (isLoading) DialogProgress()
    if (messageToast != 0){
        ToastCustom(text = stringResource(id = messageToast))
        loginViewModel.detectAction(idAction = 0)
    }
}