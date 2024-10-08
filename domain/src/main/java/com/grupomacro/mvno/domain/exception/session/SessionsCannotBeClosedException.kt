package com.grupomacro.mvno.domain.exception.session

import com.grupomacro.mvno.domain.core.exception.DomainException

data class SessionsCannotBeClosedException(val msg: String = "") : DomainException(msg)