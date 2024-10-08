package com.grupomacro.mvno.domain.core.exception

data class NotSupportedUserCredentialsException(val msg: String) : DomainException(msg)