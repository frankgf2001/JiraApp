package com.aquadevs.jira.presentation.navigation

sealed class MainRoute(val route:String) {
    data object NavProfileScreen:MainRoute("navProfileScreen")
    data object NavHomeScreen:MainRoute("navHomeScreen")
    data object NavNewProjectScreen:MainRoute("navNewProjectScreen")
    data object NavEditPhotoScreen:MainRoute("navEditPhotoScreen")
}