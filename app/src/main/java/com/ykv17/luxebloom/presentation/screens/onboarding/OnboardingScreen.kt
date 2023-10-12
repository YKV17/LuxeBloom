package com.ykv17.luxebloom.presentation.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ykv17.luxebloom.presentation.screens.onboarding.state.OnboardingPager
import com.ykv17.luxebloom.presentation.route.RouteScreens
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pageCount = 4
    val pageState = rememberPagerState {
        pageCount
    }
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Box {
            HorizontalPager(
                state = pageState, modifier = Modifier.fillMaxWidth()
            ) { page ->
                OnboardingPage(pageData = viewModel.onboardingPagerList[page])
            }

            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                HorizontalPagerIndicator(
                    pageCount,
                    pageState,
                    modifier = Modifier
                        .padding(16.dp)
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    indicatorModifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .height(8.dp)
                        .width(8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (pageState.currentPage != pageCount - 1) {
                        Text(
                            text = "Skip",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                navController.navigate(RouteScreens.HomeScreen.route) {
                                    popUpTo(RouteScreens.OnBoardingScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }
                    Button(onClick = {
                        coroutineScope.launch {
                            if (pageState.currentPage == pageCount - 1) {
                                navController.navigate(RouteScreens.HomeScreen.route) {
                                    popUpTo(RouteScreens.OnBoardingScreen.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                pageState.animateScrollToPage(pageState.currentPage + 1)
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "Next")
                    }
                }
            }


        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pageCount: Int,
    pagerState: PagerState,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.inversePrimary,
    modifier: Modifier = Modifier.wrapContentHeight(),
    indicatorModifier: Modifier = Modifier
        .padding(2.dp)
        .clip(CircleShape)
        .size(20.dp)
) {
    Row(
        modifier, horizontalArrangement = Arrangement.Center
    ) {
        repeat(pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) activeColor else inactiveColor
            Box(
                modifier = indicatorModifier.background(color)
            )
        }
    }
}

@Composable
fun OnboardingPage(pageData: OnboardingPager) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = pageData.drawable),
            contentDescription = pageData.title,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = pageData.title,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = pageData.description,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

