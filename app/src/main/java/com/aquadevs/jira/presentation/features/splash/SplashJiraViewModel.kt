package com.aquadevs.jira.presentation.features.splash

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.jira.core.function.Composable.changeActivity
import com.aquadevs.jira.presentation.features.login.LoginActivity
import com.aquadevs.jira.presentation.features.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashJiraViewModel @Inject constructor(

) : ViewModel() {

    fun changeActivity(activity: Activity){
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            withContext(Dispatchers.Main){
                val res = false
                if (res) activity.changeActivity(MainActivity())
                else activity.changeActivity(LoginActivity())
            }
        }
    }
}