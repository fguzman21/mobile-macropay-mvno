package com.grupomacro.mvno.domain.exception.session

import com.grupomacro.mvno.domain.core.exception.DomainException

data class UserNotFoundLocalException(val credential: String) : DomainException(credential)

