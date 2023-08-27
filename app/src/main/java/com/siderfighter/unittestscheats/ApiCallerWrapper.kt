package com.siderfighter.unittestscheats

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext

suspend fun <Response> callApiAsFlow(
    request: suspend () -> Response
): Flow<Response> {
    return withContext(Dispatchers.IO) {
        flowOf(request.invoke())
    }
}