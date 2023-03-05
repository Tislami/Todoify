package com.tis.todoify.utils.extensions

import androidx.compose.runtime.Composable

@Composable
fun <T> List<T>.forEachFirstThree(action: @Composable (T) -> Unit) {
    for (i in 0..2) {
        if (i >= size) return
        action(this[i])
    }
}