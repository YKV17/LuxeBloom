package com.ykv17.luxebloom.presentation.route

sealed class RouteScreens(val route: String) {
    object OnBoardingScreen : RouteScreens("onboarding_screen")
    object LoginScreen : RouteScreens("login_screen")
    object HomeScreen : RouteScreens("home_screen")
    object CategoryScreen : RouteScreens("category_screen")
    object AccountScreen : RouteScreens("account_screen")
    object ProductScreen : RouteScreens("product_screen")
}
