package com.ykv17.luxebloom.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ykv17.luxebloom.presentation.screens.onboarding.OnboardingViewModel
import com.ykv17.luxebloom.presentation.route.RouteScreens
import com.ykv17.luxebloom.presentation.screens.home.HomeScreen
import com.ykv17.luxebloom.presentation.screens.login.LoginScreen
import com.ykv17.luxebloom.presentation.screens.navigation_bar.BottomNavigationBar
import com.ykv17.luxebloom.presentation.screens.onboarding.OnboardingScreen
import com.ykv17.luxebloom.presentation.screens.product.ProductDetailScreen
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
                    composable(route = RouteScreens.ProductScreen.route + "/{productId}",
                        arguments = listOf(
                            navArgument("productId") {
                                type = NavType.StringType
                            }
                        )
                    ) {

                        val productId = remember {
                            it.arguments?.getString("productId") ?: ""
                        }

                        ProductDetailScreen(navController = navController, productId = productId)
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