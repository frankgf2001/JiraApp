package com.aquadevs.jira.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aquadevs.jira.ui.validateTheme

@Composable
fun OutlinedTextFieldCustom(
    value: String,
    label: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 20,
    maxLine: Int = 1,
    maxLength : Int = 25,
    keyBoardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    @DrawableRes trailingIcon: Int? = null,
    trailingIconIV: ImageVector? = null,
    sizeIcon:Int = 20,
    onClickTrailingIcon: (() -> Unit)? = null,
    onValue: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.height(60.dp),
        value = value,
        onValueChange = {
            if(it.length <= maxLength){
                onValue(it)
            }
        },
        textStyle = TextStyle(
            textAlign = TextAlign.Start,
            color = validateTheme().onPrimary,
            fontSize = 14.sp
        ),
        label = {
            TextCustom(
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = validateTheme().primary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType
        ),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = validateTheme().primary,
            focusedBorderColor = validateTheme().primary,
            focusedLabelColor = validateTheme().primary,
            unfocusedBorderColor = validateTheme().secondary,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledBorderColor = validateTheme().secondary,
            focusedPlaceholderColor = validateTheme().primary,
            focusedLeadingIconColor = validateTheme().primary,
            focusedTrailingIconColor = validateTheme().primary
        ),
        enabled = enabled,
        shape = RoundedCornerShape(cornerRadius),
        maxLines = maxLine,
        visualTransformation = visualTransformation,
        trailingIcon = if (trailingIcon != null || trailingIconIV != null) {
            {
                IconButton(
                    onClick = {
                        onClickTrailingIcon?.let {
                            it()
                        }
                    }
                ) {
                    if (trailingIcon != null ){
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            contentDescription = null,
                            modifier = Modifier.size(sizeIcon.dp),
                            tint = validateTheme().primary
                        )
                    }

                    if (trailingIconIV != null ){
                        Icon(
                            imageVector = trailingIconIV,
                            contentDescription = null,
                            modifier = Modifier.size(sizeIcon.dp),
                            tint = validateTheme().primary
                        )
                    }
                }
            }
        } else null,
        singleLine = true
    )
}