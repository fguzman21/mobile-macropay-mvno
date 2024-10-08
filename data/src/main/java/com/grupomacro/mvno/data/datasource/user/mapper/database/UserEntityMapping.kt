package com.grupomacro.mvno.data.datasource.user.mapper.database

import com.grupomacro.mvno.database.entity.UserEntity
import com.grupomacro.mvno.domain.user.model.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.userId,
        clientId = this.clientId,
        contractId = this.contractId,
        sl = this.sl,
        fullName = this.fullName,
        preferredName = this.preferredName,
        email = this.email,
        curp = this.curp,
        associatedSl = this.associatedSl
    )
}

fun UserEntity.toDomain(): User {
    return User(
        userId = this.id,
        clientId = this.clientId,
        contractId = this.contractId,
        sl = this.sl,
        preferredName = this.preferredName,
        fullName = this.fullName,
        email = this.email,
        curp = this.curp,
        associatedSl = this.associatedSl,
    )
}
