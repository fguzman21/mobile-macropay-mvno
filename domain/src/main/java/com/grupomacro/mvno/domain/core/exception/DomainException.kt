package com.grupomacro.mvno.domain.core.exception

open class DomainException(
    override val message: String,
    override val cause: Exception?,
) : Exception() {

    constructor() : this("", null)

    constructor(message: String) : this(message, null)

    constructor(cause: Exception?) : this("", cause)
}
