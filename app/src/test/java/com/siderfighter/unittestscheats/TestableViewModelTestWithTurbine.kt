package com.siderfighter.unittestscheats

import app.cash.turbine.test
import com.siderfighter.unittestscheats.utils.TestDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class TestableViewModelTestWithTurbine {

    private val testDispatcher = TestDispatchers()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher.testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun updateSharedFlow() = runTest(testDispatcher.testDispatcher) {
        val viewModel = TestableViewModel(testDispatcher)

        viewModel.updateSharedFlow(1)
        viewModel.sharedFlow.test {
            advanceUntilIdle()
            val value = awaitItem()
            println("siderfighter -> inside turbine -> value: $value")
            assert(value == "1")
        }
    }

    @Test
    fun testCountDownFlow() = runTest {
        val viewModel = TestableViewModel(testDispatcher)

        viewModel.updateCountdown(100)

        viewModel.countdown.test {
            for (i in 100 downTo 0) {
                val count = awaitItem()
                println("siderfighter -> count = $count, i = $i")
                assert(count == i)
                advanceTimeBy(1000)
            }
            cancelAndConsumeRemainingEvents()
        }
    }
}