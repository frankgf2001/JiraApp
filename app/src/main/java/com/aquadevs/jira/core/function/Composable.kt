package com.aquadevs.jira.core.function

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavHostController

object Composable {
    fun NavHostController.navigateCustom(route: String) {
        this.popBackStack()
        this.navigate(route)
    }

    fun Activity.changeActivity(activity: Activity){
        startActivity(Intent(this, activity::class.java))
        finish()
    }
}
