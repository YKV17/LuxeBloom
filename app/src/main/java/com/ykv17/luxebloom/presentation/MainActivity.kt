package com.ykv17.luxebloom.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ykv17.luxebloom.presentation.screens.onboarding.OnboardingViewModel
import com.ykv17.luxebloom.presentation.route.RouteScreens
import com.ykv17.luxebloom.presentation.screens.home.HomeScreen
import com.ykv17.luxebloom.presentation.screens.login.LoginScreen
import com.ykv17.luxebloom.presentation.screens.onboarding.OnboardingScreen
import com.ykv17.luxebloom.ui.theme.LuxeBloomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuxeBloomTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = RouteScreens.OnBoardingScreen.route
                ) {
                    composable(RouteScreens.OnBoardingScreen.route) {
                        OnboardingScreen(navController = navController)
                    }
                    composable(RouteScreens.LoginScreen.route) {
                        LoginScreen(navController = navController)
                    }
                    composable(RouteScreens.HomeScreen.route) {
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LuxeBloomTheme {
        Greeting("Android")
    }
}