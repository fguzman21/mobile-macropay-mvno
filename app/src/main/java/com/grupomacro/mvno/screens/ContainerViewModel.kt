package com.grupomacro.mvno.screens

import androidx.lifecycle.viewModelScope
import com.grupomacro.mvno.core.presentation.StateActionViewModel
import com.grupomacro.mvno.domain.session.usecase.GetSessionDataUseCase
import com.grupomacro.mvno.screens.container.presentation.mapper.toAction
import com.grupomacro.mvno.screens.container.presentation.model.ContainerAction
import com.grupomacro.mvno.screens.container.presentation.model.ContainerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(
    private val getSessionDataUseCase: GetSessionDataUseCase,
) : StateActionViewModel<ContainerUiState, ContainerAction>() {

    override val initialState: ContainerUiState
        get() = ContainerUiState.initialState

    override fun dispatchAction(action: ContainerAction?) {
        when (action) {

            is ContainerAction.ScreenStartedAction -> {
                viewModelScope.launch {
                    val useCaseResult = getSessionDataUseCase()
                    sendAction(useCaseResult.toAction())
                    println(useCaseResult)
                }
            }

            is ContainerAction.ScreenStoppedAction -> Unit

            is ContainerAction.UpdateUserGreeting -> {
                updateState {
                    this.copy(
                        partOfDay = action.partOfDay,
                        userDisplayedName = action.userName
                    )
                }
            }

            is ContainerAction.ShowNotificationsSection -> {
                action.callback()
            }

            null -> Unit
        }
    }
}
