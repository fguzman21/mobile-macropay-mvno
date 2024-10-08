package com.grupomacro.mvno.domain.exception.session

import com.grupomacro.mvno.domain.core.exception.DomainException

data class MissingUserCredentialsException(val msg: String) : DomainException(msg)