package com.siderfighter.unittestscheats.config

import kotlinx.coroutines.flow.Flow

interface IConfigParamSource {
    fun <T> getConfigsAndInvoke(action: suspend (authToken: String, authId: String) -> T): Flow<T>
}