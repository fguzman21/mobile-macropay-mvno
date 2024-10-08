package com.grupomacro.mvno.domain.core.collection

fun <T> List<T>.hasZero() = this.isEmpty()

fun <T> List<T>.hasOne() = this.size == 1
