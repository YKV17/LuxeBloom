package com.ykv17.luxebloom.util

import androidx.compose.runtime.MutableState

object StateUtils {
    fun <T> updateState(state: MutableState<T>, block: (value: T) -> T) {
        state.value = block(state.value)
    }
}