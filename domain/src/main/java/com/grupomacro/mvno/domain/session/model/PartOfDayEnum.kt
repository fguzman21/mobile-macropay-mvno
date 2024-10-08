package com.grupomacro.mvno.domain.session.model

import java.util.Calendar

enum class PartOfDayEnum {
    DAY,
    AFTERNOON,
    NIGHT;

    companion object {

        private const val START_DAY = 5
        private const val START_AFTERNOON = 12
        private const val START_NIGHT = 19

        fun getCurrentPartOfDay(): PartOfDayEnum {
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            return when (hour) {
                in START_DAY until START_AFTERNOON -> DAY
                in START_AFTERNOON until START_NIGHT -> AFTERNOON
                else -> NIGHT
            }
        }
    }
}
