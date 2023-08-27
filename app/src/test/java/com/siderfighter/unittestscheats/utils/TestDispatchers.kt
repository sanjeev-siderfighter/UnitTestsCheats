package com.siderfighter.unittestscheats.utils

import com.siderfighter.unittestscheats.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatchers : CoroutineDispatcherProvider {
    val testDispatcher = StandardTestDispatcher()

    override val main: CoroutineDispatcher
        get() = testDispatcher
    override val io: CoroutineDispatcher
        get() = testDispatcher
    override val default: CoroutineDispatcher
        get() = testDispatcher

    fun cancel() {
        testDispatcher.cancel()
    }
}