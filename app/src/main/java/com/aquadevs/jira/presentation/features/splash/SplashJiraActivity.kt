package com.aquadevs.jira.presentation.features.splash

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashJiraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                ChangeActivity()
            }
        }
    }
}

@Composable
private fun ChangeActivity(splashViewModel: SplashJiraViewModel = hiltViewModel()) {
    val context = LocalContext.current as Activity
    splashViewModel.changeActivity(context)
}

