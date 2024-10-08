package com.grupomacro.mvno.core.presentation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import com.grupomacro.mvno.core.presentation.ActionFiltering.NONE
import com.grupomacro.mvno.core.presentation.ActionFiltering.DEBOUNCE
import com.grupomacro.mvno.core.presentation.ActionFiltering.DISTINCT

interface Action {
    val actionFiltering: ActionFiltering
}

enum class ActionFiltering {
    NONE,
    DEBOUNCE,
    DISTINCT,
}

fun <T : Action> Flow<T?>.filterActions(type: ActionFiltering): Flow<T?> {
    return this.filter {
        when (type) {
            NONE -> it?.actionFiltering == type || it == null
            DEBOUNCE -> it?.actionFiltering == type
            DISTINCT -> it?.actionFiltering == type
        }
    }
}

infix fun Action?.isSameAction(that: Action?): Boolean {
    return when {
        this == null -> that == null
        that == null -> false
        else -> this::class == that::class
    }
}
