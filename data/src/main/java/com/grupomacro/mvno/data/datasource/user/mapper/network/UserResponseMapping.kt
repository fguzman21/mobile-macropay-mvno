package com.grupomacro.mvno.data.datasource.user.mapper.network

import com.grupomacro.mvno.domain.user.model.User
import com.grupomacro.mvno.network.network.api.login.model.response.UserResponse

fun UserResponse.toDomain(): User {
    return User(
        userId = 0L,
        clientId = this.idUsuario,
        contractId = this.cveContrato,
        sl = this.cveSolicitud,
        preferredName = "",
        fullName = this.nombre,
        email = this.correoElectronico,
        curp = this.curp,
        associatedSl = this.slAsociada ?: "",
    )
}
