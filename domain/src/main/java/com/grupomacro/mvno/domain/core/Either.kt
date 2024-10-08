package com.grupomacro.mvno.domain.core

import com.grupomacro.mvno.domain.core.exception.DomainException

@JvmInline
value class Either<out T> private constructor(private val content: Any?) {

    val isValue: Boolean get() = content !is Failure

    val isFailure: Boolean get() = content is Failure

    fun getValueOrNull(): T? = when {
        isFailure -> null
        else -> content as T
    }

    fun getFailureOrNull() = when (content) {
        is Failure -> content.exception
        else -> null
    }

    @Suppress("UseCheckOrError")
    fun getValue(): T = when {
        isFailure -> throw IllegalStateException("Value expected, not Failure")
        else -> content as T
    }

    fun getOrThrow(): T {
        return when (val exception = getFailureOrNull()) {
            null -> content as T
            else -> throw exception
        }
    }

    private data class Failure(val exception: DomainException)

    companion object {

        fun <T> value(value: T): Either<T> = Either(value)

        fun <T> failure(exception: DomainException): Either<T> = Either(Failure(exception))
    }
}

fun <T, R> Either<T>.map(transform: (T) -> R): Either<R> {
    return when {
        isFailure -> Either.failure(requireNotNull(getFailureOrNull()))
        else -> Either.value(transform(getValue()))
    }
}

suspend fun <T, R> Either<T>.then(transform: suspend (T) -> Either<R>): Either<R> {
    return when {
        isFailure -> Either.failure(requireNotNull(getFailureOrNull()))
        else -> transform(getValue())
    }
}

suspend fun <T, R> Either<T>.besides(transform: suspend (T) -> Either<R>): Either<T> {
    return when {
        isFailure -> this
        else -> {
            val actionResult = transform(getValue())
            when {
                actionResult.isFailure -> Either.failure(requireNotNull(actionResult.getFailureOrNull()))
                else -> this
            }
        }
    }
}

suspend fun <T, R> Either<T>.besidesIf(applyOperation: (T) -> Boolean, transform: suspend (T) -> Either<R>): Either<T> {
    return when {
        isFailure -> this
        applyOperation(getValue()) -> this.besides(transform)
        else -> this
    }
}

fun <T, R> Either<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: DomainException) -> R
): R {
    return when {
        isValue -> onSuccess(this.getValue())
        else -> onFailure(requireNotNull(this.getFailureOrNull()))
    }
}

inline fun <T> Either<T>.getOrElse(onFailure: (exception: DomainException) -> T): T {
    return when (val exception = getFailureOrNull()) {
        null -> getValue()
        else -> onFailure(exception)
    }
}

inline fun <T> Either<T>.getOrReturn(onFailure: (exception: DomainException) -> T): T {
    return when (val exception = getFailureOrNull()) {
        null -> getValue()
        else -> return onFailure(exception)
    }
}

suspend fun <T, R> T.runAndCatch(block: suspend T.() -> R): Either<R> {
    return try {
        Either.value(block())
    } catch (e: DomainException) {
        Either.failure(e)
    } catch (e: Exception) {
        Either.failure(DomainException(e))
    } catch (e: Throwable) {
        Either.failure(DomainException(Exception(e)))
    }
}

fun <A, B> suspendLambda(block: suspend (A) -> B): suspend (A) -> B {
    return block
}
