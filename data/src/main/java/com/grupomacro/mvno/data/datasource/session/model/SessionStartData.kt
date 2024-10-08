package com.grupomacro.mvno.data.datasource.session.model

import com.grupomacro.mvno.domain.session.model.Session
import com.grupomacro.mvno.domain.user.model.User
import com.grupomacro.mvno.domain.user.model.UserEmailCollisionItem


data class SessionStartData(
    val session: Session,
    val user: User?,
    val userCollision: List<UserEmailCollisionItem>,
)
