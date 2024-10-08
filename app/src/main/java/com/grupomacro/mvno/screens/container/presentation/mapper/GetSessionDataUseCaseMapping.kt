package com.grupomacro.mvno.screens.container.presentation.mapper

import com.grupomacro.mvno.core.ui.utils.formatAsUserName
import com.grupomacro.mvno.domain.session.model.PartOfDayEnum
import com.grupomacro.mvno.domain.session.usecase.GetSessionDataUseCase
import com.grupomacro.mvno.screens.container.presentation.model.ContainerAction


fun GetSessionDataUseCase.UseCaseResult.toAction(): ContainerAction? {
    return when (this) {
        is GetSessionDataUseCase.UseCaseResult.Success -> {
            val userNameDisplayed = session.user.run {
                preferredName.ifEmpty { fullName }
            }.formatAsUserName()
            ContainerAction.UpdateUserGreeting(
                userName = userNameDisplayed,
                partOfDay = partOfDayEnum,
            )
        }

        is GetSessionDataUseCase.UseCaseResult.Error -> {
            ContainerAction.UpdateUserGreeting(
                userName = "Bienvenido",
                partOfDay = PartOfDayEnum.DAY,
            )
        }
    }
}
