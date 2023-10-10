package com.ykv17.luxebloom.presentation.screens.onboarding.models

import androidx.lifecycle.ViewModel
import com.ykv17.luxebloom.R

class OnboardingViewModel : ViewModel() {

    val onboardingPagerList: List<OnboardingPager> by lazy {
        listOf(
            OnboardingPager(R.drawable.ic_launcher_foreground, "Welcome", ""),
            OnboardingPager(R.drawable.explore_product, "Explore Products", ""),
            OnboardingPager(R.drawable.online_payment, "Ease Payments", ""),
            OnboardingPager(R.drawable.home_delivery, "Quick Delivery", "")
        )
    }
}