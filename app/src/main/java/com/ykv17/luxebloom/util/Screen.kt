package com.ykv17.luxebloom.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object Screen {
    @Composable
    fun getWindowHeight(): Int{
        return with(LocalConfiguration.current) {
            screenHeightDp
        }
    }

    @Composable
    fun getWindowWidth(): Int{
        return with(LocalConfiguration.current) {
            screenWidthDp
        }
    }
}