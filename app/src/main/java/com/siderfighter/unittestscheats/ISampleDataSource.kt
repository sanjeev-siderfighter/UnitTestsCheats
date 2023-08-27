package com.siderfighter.unittestscheats

import kotlinx.coroutines.flow.Flow

interface ISampleDataSource {
    suspend fun getSampleData(): Flow<SampleData>
}