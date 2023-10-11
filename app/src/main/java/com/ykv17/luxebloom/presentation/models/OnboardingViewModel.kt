package com.ykv17.luxebloom.presentation.models

import androidx.lifecycle.ViewModel
import com.ykv17.luxebloom.R

class OnboardingViewModel : ViewModel() {

    val onboardingPagerList: List<OnboardingPager> by lazy {
        listOf(
            OnboardingPager(R.drawable.say_hello_to_new_people, "Hello", "Welcome to LuxeBloom, an online shopping app."),
            OnboardingPager(R.drawable.explore_product, "Explore Products", "Discover a world of endless shopping possibilities with our app. Explore products tailored to your preferences and interests."),
            OnboardingPager(R.drawable.online_payment, "Ease Payments", "Simplify your shopping experience with easy and secure payments. Pay with confidence and convenience."),
            OnboardingPager(R.drawable.home_delivery, "Quick Delivery", "Get what you want, when you want it. Our quick delivery ensures your purchases are at your doorstep in no time.")
        )
    }
}