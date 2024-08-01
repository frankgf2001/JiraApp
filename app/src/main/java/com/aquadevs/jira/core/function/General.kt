package com.aquadevs.jira.core.function

import android.app.Activity
import android.content.Intent
import android.util.Patterns
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aquadevs.jira.R
import com.aquadevs.jira.presentation.common.DialogCustom

object General {
    fun NavHostController.navigateCustom(route: String) {
        this.popBackStack()
        this.navigate(route)
    }

    fun Activity.changeActivity(activity: Activity){
        startActivity(Intent(this, activity::class.java))
        finish()
    }

    fun String.isValidEmail():Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    @Composable
    fun AnimationLottie(
        modifier: Modifier = Modifier,
        size: Int,
        @RawRes lottie: Int,
        isPlaying:Boolean = true
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(lottie))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = isPlaying,
            modifier = modifier
                .size(size.dp)
                .background(Color.Transparent)
        )
    }

    @Composable
    fun DialogProgress(){
        DialogCustom(modifier = Modifier, onDismissRequest = { /*TODO*/ }) {
            AnimationLottie(size = 140, lottie = R.raw.progress)
        }
    }
}

