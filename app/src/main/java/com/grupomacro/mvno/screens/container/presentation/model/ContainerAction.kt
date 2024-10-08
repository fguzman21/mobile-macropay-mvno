package com.grupomacro.mvno.screens.container.presentation.model

import com.grupomacro.mvno.core.presentation.Action
import com.grupomacro.mvno.core.presentation.ActionFiltering
import com.grupomacro.mvno.domain.session.model.PartOfDayEnum

sealed class ContainerAction(
    override val actionFiltering: ActionFiltering = ActionFiltering.NONE
) : Action {

    data object ScreenStartedAction : ContainerAction(ActionFiltering.DISTINCT)

    data object ScreenStoppedAction : ContainerAction()

    data class UpdateUserGreeting(val userName: String, val partOfDay: PartOfDayEnum) : ContainerAction()

    data class ShowNotificationsSection(val callback: () -> Unit) : ContainerAction(ActionFiltering.NONE)
}
