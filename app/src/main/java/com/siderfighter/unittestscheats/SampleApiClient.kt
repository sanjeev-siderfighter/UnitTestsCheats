package com.siderfighter.unittestscheats

interface SampleApiClient {
    suspend fun getSampleData(): SampleData
    suspend fun getDependentData(authToken: String, authId: String): SampleData
}

class SampleApiClientImpl : SampleApiClient {
    override suspend fun getSampleData(): SampleData {
        return SampleData("sample")
    }

    override suspend fun getDependentData(authToken: String, authId: String): SampleData {
        return SampleData("dependent -> $authToken -> $authId")
    }

}

data class SampleData(
    val name: String,
)