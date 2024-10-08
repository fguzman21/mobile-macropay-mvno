package com.grupomacro.mvno.domain.core.exception

data class RemoteDataSourceException(
    override val message: String,
    override val cause: Exception?
) : DomainException(message, cause) {

    constructor(message: String) : this(message, null)
}