package com.grupomacro.mvno.core.ui.composables

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.LifecycleStartEffect
import com.grupomacro.mvno.core.presentation.Action
import com.grupomacro.mvno.core.presentation.StateActionViewModel

@Composable
fun <ACTION : Action> LaunchLifecycleActions(
    viewModel: StateActionViewModel<*, ACTION>,
    screenStartedAction: ACTION? = null,
    screenStoppedAction: ACTION? = null
) {
    LifecycleStartEffect(Unit) {
        viewModel.sendAction(screenStartedAction)
        onStopOrDispose {
            viewModel.sendAction(screenStoppedAction)
        }
    }
}
