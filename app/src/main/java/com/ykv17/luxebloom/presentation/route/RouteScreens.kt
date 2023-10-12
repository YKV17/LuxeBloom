package com.ykv17.luxebloom.presentation.route

sealed class RouteScreens(val route: String) {
    object OnBoardingScreen : RouteScreens("onboarding_screen")
    object LoginScreen : RouteScreens("login_screen")
    object HomeScreen : RouteScreens("home_screen")
}
