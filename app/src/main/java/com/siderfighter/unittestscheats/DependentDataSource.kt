package com.siderfighter.unittestscheats

import com.siderfighter.unittestscheats.config.AuthIdParamSource
import com.siderfighter.unittestscheats.config.AuthTokenParamSource
import com.siderfighter.unittestscheats.config.ConfigParamSource
import com.siderfighter.unittestscheats.config.IConfigParamSource
import kotlinx.coroutines.flow.Flow

class DependentDataSource(
    private val sampleApiClient: SampleApiClient = SampleApiClientImpl(),
    private val configParamSource: IConfigParamSource = ConfigParamSource(AuthTokenParamSource(), AuthIdParamSource()),
    private val dispatcher: CoroutineDispatcherProvider = DefaultCoroutineDispatchers()
) : ISampleDataSource {
    override suspend fun getSampleData(): Flow<SampleData> {
        return configParamSource.getConfigsAndInvoke { authToken, authId ->
            val x = sampleApiClient.getDependentData(authToken = authToken, authId = authId)
            println("batman -> x = $x")
            x
        }
    }
}