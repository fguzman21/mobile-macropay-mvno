package com.grupomacro.mvno.domain.session.usecase

import com.grupomacro.mvno.domain.session.model.PartOfDayEnum
import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.session.repository.SessionRepository
import javax.inject.Inject

class GetSessionDataUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): UseCaseResult {
        return try {
            val session = sessionRepository.getCurrentSession()
            UseCaseResult.Success(session, PartOfDayEnum.getCurrentPartOfDay())
        } catch (e: Exception) {
            UseCaseResult.Error("${e.javaClass::class.simpleName}: ${e.message}")
        }
    }

    sealed class UseCaseResult {
        data class Success(val session: Session, val partOfDayEnum: PartOfDayEnum) : UseCaseResult()
        data class Error(val msg: String) : UseCaseResult()
    }
}
