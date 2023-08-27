package com.siderfighter.unittestscheats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TestableViewModel(private val dispatcher: CoroutineDispatcherProvider = DefaultCoroutineDispatchers()) :
    ViewModel() {
    private val _sharedFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _countdown = MutableSharedFlow<Int>(extraBufferCapacity = 1)
    val countdown = _countdown.asSharedFlow()

    fun updateSharedFlow(num: Int) {
        viewModelScope.launch(dispatcher.io) {
            val sol = num.toString()
            _sharedFlow.tryEmit(sol)
        }
    }

    fun updateCountdown(num: Int) {
        viewModelScope.launch(dispatcher.io) {
            for (i in num downTo 0) {
                delay(1000)
                _countdown.tryEmit(i)
            }
        }
    }

}