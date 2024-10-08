package com.grupomacro.mvno.network.network.api.login.model.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

    @SerializedName("user")
    val user: UserResponse,

    @SerializedName("token")
    val token: String,
)

data class UserResponse(

    @SerializedName("correo_electronico")
    val correoElectronico: String,

    @SerializedName("curp")
    val curp: String,

    @SerializedName("cve_cliente")
    val cveCliente: String,

    @SerializedName("cve_contrato")
    val cveContrato: String,

    @SerializedName("cve_solicitud")
    val cveSolicitud: String,

    @SerializedName("dispositivoId")
    val dispositivoId: String,

    @SerializedName("idSession")
    val idSession: String,

    @SerializedName("idUsuario")
    val idUsuario: String,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("sl_asociada")
    val slAsociada: String?,

    @SerializedName("telefono_celular")
    val telefonoCelular: String,
)
