package com.grupomacro.mvno.core.ui.utils

import java.util.Locale

fun String.formatAsUserName(): String {
    return this
        .split("\\s+".toRegex())
        .take(2)
        .joinToString(" ") { part ->
            part.lowercase()
                .replaceFirstChar { c ->
                    c.titlecase(Locale.getDefault())
                }
        }
}
