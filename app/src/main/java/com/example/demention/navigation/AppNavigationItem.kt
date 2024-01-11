package com.example.demention.navigation

sealed class AppNavigationItem(val route: String) {

    object Pass: AppNavigationItem("pass")

    object Location: AppNavigationItem("location")
}