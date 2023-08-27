package com.siderfighter.unittestscheats

import com.siderfighter.unittestscheats.utils.TestDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn

class SampleDataSourceTest {

    private val testDispatcher = TestDispatchers()

    private val sampleApiClient = Mockito.mock(SampleApiClient::class.java)

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
    fun `getSampleData should return a Flow of SampleData`() = runTest {
        // Given
        Mockito.`when`(sampleApiClient.getSampleData()).doReturn(SampleData("Sanjeev Kumar"))

        val dataSource = SampleDataSource(sampleApiClient, testDispatcher)

        // When
        val flow = dataSource.getSampleData()

        // Then
        advanceUntilIdle()

        val result = flow.first()

        println("batman -> result = $result")
        assert(result.name == "Sanjeev Kumar")
    }
}