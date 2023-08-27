package com.siderfighter.unittestscheats

import com.siderfighter.unittestscheats.utils.AUTH_ID
import com.siderfighter.unittestscheats.utils.AUTH_TOKEN
import com.siderfighter.unittestscheats.utils.TestDispatchers
import com.siderfighter.unittestscheats.utils.fake.FakeConfigParamSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doReturn

class DependentDataSourceTest {
    private val sampleApiClient = Mockito.mock(SampleApiClient::class.java)

    private val testDispatcher = TestDispatchers()

    @Test
    fun `test getting the dependent data`() = runTest {
        Mockito.`when`(sampleApiClient.getDependentData(AUTH_TOKEN, AUTH_ID)).doReturn(
            SampleData("Sanjeev Kumar")
        )

        val dataSource = DependentDataSource(
            sampleApiClient = sampleApiClient,
            configParamSource = FakeConfigParamSource(),
            dispatcher = testDispatcher
        )

        val flow = dataSource.getSampleData()

        val result = flow.first()

        println("batman -> result = $result")
        assert(result.name == "Sanjeev Kumar")
    }
}