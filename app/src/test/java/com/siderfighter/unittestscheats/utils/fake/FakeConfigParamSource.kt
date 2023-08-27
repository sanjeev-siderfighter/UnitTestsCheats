package com.siderfighter.unittestscheats.utils.fake

import com.siderfighter.unittestscheats.config.IConfigParamSource
import com.siderfighter.unittestscheats.utils.AUTH_ID
import com.siderfighter.unittestscheats.utils.AUTH_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class FakeConfigParamSource : IConfigParamSource {
    override fun <T> getConfigsAndInvoke(action: suspend (authToken: String, authId: String) -> T): Flow<T> {
        return combine(
            flowOf(AUTH_TOKEN),
            flowOf(AUTH_ID)
        ) { authToken, authId ->
            action.invoke(authToken, authId)
        }
    }
}