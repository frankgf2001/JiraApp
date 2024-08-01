package com.aquadevs.jira.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aquadevs.jira.presentation.features.main.editPhoto.EditPhotoScreen
import com.aquadevs.jira.presentation.features.main.home.HomeScreen
import com.aquadevs.jira.presentation.features.main.newProject.NewProjectScreen
import com.aquadevs.jira.presentation.features.main.profile.ProfileScreen
import com.aquadevs.jira.ui.validateTheme

@Composable
fun NavMainMenu(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainRoute.NavHomeScreen.route,
        modifier = modifier.fillMaxSize().background(validateTheme().background)
    ) {
        composable(route = MainRoute.NavHomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = MainRoute.NavNewProjectScreen.route) {
            NewProjectScreen(navController = navController)
        }

        composable(route = MainRoute.NavProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = MainRoute.NavEditPhotoScreen.route) {
            EditPhotoScreen(navController = navController)
        }
    }
}