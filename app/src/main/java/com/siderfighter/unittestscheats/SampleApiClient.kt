package com.siderfighter.unittestscheats

interface SampleApiClient {
    suspend fun getSampleData(): SampleData
}

class SampleApiClientImpl() : SampleApiClient {
    override suspend fun getSampleData(): SampleData {
        return SampleData("sample")
    }

}

data class SampleData(
    val name: String,
)