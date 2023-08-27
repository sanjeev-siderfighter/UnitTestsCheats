package com.siderfighter.unittestscheats

import com.siderfighter.unittestscheats.utils.TestDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class TestableViewModelTest {

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
        var ans = "empty"

        backgroundScope.launch(UnconfinedTestDispatcher(testDispatcher.testDispatcher.scheduler)) {
            viewModel.sharedFlow.collectLatest {
                println("siderfighter -> in collect : value: $it")
                ans = it
                assert(it == "1")
            }
        }
        viewModel.updateSharedFlow(1)
        advanceUntilIdle()
        println("siderfighter -> outside -> ans: $ans")
        assert(ans == "1")
    }

    @Test
    fun testCountDownFlow() = runTest {
        val viewModel = TestableViewModel(testDispatcher)

        val resultList = mutableListOf<Int>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.countdown.collectLatest {
                resultList.add(it)
            }
        }
        viewModel.updateCountdown(100)
        advanceUntilIdle()

        println("siderfighter -> resultList = $resultList")
        assert(resultList.size == 101)
        resultList.forEachIndexed { index, i ->
            println("siderfighter -> resultList[$index] = $i")
            assert(i == 100 - index)
        }
    }
}