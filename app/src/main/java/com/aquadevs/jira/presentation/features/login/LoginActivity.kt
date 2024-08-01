package com.aquadevs.jira.presentation.features.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.features.main.editPhoto.EditPhotoScreen
import com.aquadevs.jira.ui.validateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.background(validateTheme().background)) {
                EditPhotoScreen()
            //LoginScreen()
            }
        }
    }
}