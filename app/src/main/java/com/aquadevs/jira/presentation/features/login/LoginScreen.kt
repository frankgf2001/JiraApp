package com.aquadevs.jira.presentation.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.common.ButtonCustom
import com.aquadevs.jira.presentation.common.CheckBoxCustom
import com.aquadevs.jira.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.jira.presentation.common.TextCustom
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
    }
}

@Composable
fun MyFooter() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 120.dp)
    ) {
        ButtonCustom(
            textButton = stringResource(id = R.string.logIn),
            modifier = Modifier.clip(RoundedCornerShape(15)).fillMaxWidth(),
            fontSize = 16
        ) {

        }
    }
}

@Composable
fun MyBody(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 25.dp)) {
        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.email),
            modifier = Modifier.fillMaxWidth()
        ) {

        }

        OutlinedTextFieldCustom(
            value = "",
            label = stringResource(id = R.string.password),
            modifier = Modifier.fillMaxWidth(),
            keyBoardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = R.drawable.visibility_48dp,
            sizeIcon = 25
        ) {

        }

        Row(modifier = Modifier.fillMaxWidth()) {
            RememberPassword(checked = true, enabled = true) {

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
private fun MyHeader(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.icon_jira),
            contentDescription = null,
            modifier = Modifier.size(116.dp, 90.dp).padding(bottom = 10.dp)
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
}