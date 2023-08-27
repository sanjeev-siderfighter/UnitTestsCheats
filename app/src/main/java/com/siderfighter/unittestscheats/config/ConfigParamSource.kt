package com.siderfighter.unittestscheats.config

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class ConfigParamSource(
    private val authTokenParamSource: AuthTokenParamSource,
    private val authIdParamSource: AuthIdParamSource
): IConfigParamSource {
    override fun <T> getConfigsAndInvoke(action: suspend (authToken: String, authId: String) -> T): Flow<T> {
        return combine(
            flowOf(authTokenParamSource.getAuthToken()),
            flowOf(authIdParamSource.getAuthId())
        ) { authToken, authId ->
            action(authToken, authId)
        }
    }
}