package com.grupomacro.mvno.screens.container.presentation.model

import com.grupomacro.mvno.core.presentation.UIState
import com.grupomacro.mvno.domain.session.model.PartOfDayEnum

data class ContainerUiState(
    val notificationsCounting: Int,
    val partOfDay: PartOfDayEnum,
    val userDisplayedName: String,
) : UIState {

    companion object {
        val initialState = ContainerUiState(
            notificationsCounting = 3,
            partOfDay = PartOfDayEnum.DAY,
            userDisplayedName = "",
        )
    }
}
