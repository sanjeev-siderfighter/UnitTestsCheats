package com.siderfighter.unittestscheats

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SampleDataSource(
    private val sampleApiClient: SampleApiClient = SampleApiClientImpl(),
    private val dispatcher: CoroutineDispatcherProvider = DefaultCoroutineDispatchers()
) : ISampleDataSource {
    override suspend fun getSampleData(): Flow<SampleData> {
        return withContext(dispatcher.io) {
            callApiAsFlow(request = {
                sampleApiClient.getSampleData()
            })
        }
    }
}