package com.grupomacro.mvno.network.network.api.login.model.request

import com.google.gson.annotations.SerializedName


//TODO("login temporal: regresar a los valores reales")
data class UserLoginRequest(

    @SerializedName("serialId")//contrasenia
    val contrasenia: String,

    @SerializedName("correo")//correoElectronico
    val correoElectronico: String,

    @SerializedName("androidId")//dispositivoId
    val dispositivoId: String,

    @SerializedName("idApp")
    val idApp: Int,

    @SerializedName("marca")
    val marca: String,

    @SerializedName("modelo")
    val modelo: String,

    @SerializedName("operario")
    val operario: String,

    @SerializedName("sistemaOperativo")
    val sistemaOperativo: Int,

    @SerializedName("telefono")
    val telefono: String,
)
