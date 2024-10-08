package com.grupomacro.mvno.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class StateActionViewModel<STATE : UIState, ACTION : Action> : ViewModel() {

    private val _uiState by lazy { MutableStateFlow(initialState) }
    val uiState: StateFlow<STATE> get() = _uiState.asStateFlow()

    private val eventChannel = Channel<ACTION?>(Channel.UNLIMITED)
    private var channelReceivingJob: Job

    init {
        channelReceivingJob = startCollectingActions()
    }

    protected abstract val initialState: STATE

    protected abstract fun dispatchAction(action: ACTION?)

    val currentState: STATE get() = _uiState.value

    fun updateState(transition: STATE.() -> STATE) {
        _uiState.update { it.transition() }
    }

    fun sendAction(action: ACTION?) {
        viewModelScope.launch {
            eventChannel.send(action)
        }
    }

    private fun startCollectingActions(): Job {
        return viewModelScope.launch {
            val allIncomingEvents = eventChannel
                .consumeAsFlow()
                .shareIn(this, SharingStarted.Lazily, 0)
            val normalEvents = allIncomingEvents
                .filterActions(ActionFiltering.NONE)
            val notRepeatedEvents = allIncomingEvents
                .filterActions(ActionFiltering.DISTINCT)
                .distinctUntilChanged { old, new -> new isSameAction old }
            val debouncedEvents = allIncomingEvents
                .filterActions(ActionFiltering.DEBOUNCE)
                .throttleFirst(DEBOUNCE_TIME)
            merge(normalEvents, notRepeatedEvents, debouncedEvents)
                .collect { action ->
                    println("event dispatched: $action")
                    dispatchAction(action)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        channelReceivingJob.cancel()
    }

    companion object {
        const val DEBOUNCE_TIME = 500L
    }
}

/**
 * Taken from: https://proandroiddev.com/from-rxjava-to-kotlin-flow-throttling-ed1778847619
 * Previous implementation:
 * .withIndex()
 * .debounce { indexedValue -> DEBOUNCE_TIME.takeIf { indexedValue.index > 0 } ?: 0L }
 * .map { it.value }
 * */
private fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}
