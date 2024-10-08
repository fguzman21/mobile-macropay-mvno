package com.grupomacro.mvno.network.network.api.login

import android.os.Build
import com.grupomacro.mvno.network.network.api.login.model.UserLoginApiCredentials
import com.grupomacro.mvno.network.network.api.login.model.request.UserLoginRequest
import com.grupomacro.mvno.network.network.api.login.model.response.UserLoginResponse
import com.grupomacro.mvno.network.network.api.shared.model.request.BaseRequest
import com.grupomacro.mvno.network.network.api.shared.model.response.ApiResponse
import com.grupomacro.mvno.network.network.api.util.apiCallAsResult
import com.grupomacro.mvno.network.network.api.util.retryOnUnauthorized
import com.grupomacro.mvno.network.network.platform.PlatformUtils
import okhttp3.Credentials

class UserLoginApiFacade(
    private val userLoginApi: UserLoginApi,
    private val apiCredentials: UserLoginApiCredentials,
    private val platformUtils: PlatformUtils,
) {

    constructor(
        userLoginApi: UserLoginApi,
        platformUtils: PlatformUtils,
    ) : this(userLoginApi, DefaultUserLoginApiCredentials, platformUtils)

    private val basicAuthentication: String by lazy {
        Credentials.basic(apiCredentials.user, apiCredentials.password)
    }

    suspend fun loginWithMail(mail: String, password: String): Result<ApiResponse<UserLoginResponse>> {
        if (mail.isBlank()) return Result.failure(getNullLoginRequestException(mail = mail))
        val request = getLoginRequest(mail = mail, password = password)
        return apiCallAsResult {
            retryOnUnauthorized({ "" }) {
                userLoginApi.login(request, basicAuthentication)
            }
        }
    }

    suspend fun loginWithPhone(phone: String, password: String): Result<ApiResponse<UserLoginResponse>> {
        if (phone.isBlank()) return Result.failure(getNullLoginRequestException(phone = phone))
        val request = getLoginRequest(phone = phone, password = password)
        return apiCallAsResult {
            retryOnUnauthorized({ "" }) {
                userLoginApi.login(request, basicAuthentication)
            }
        }
    }

    private fun getLoginRequest(
        mail: String = "",
        phone: String = "",
        password: String,
    ): BaseRequest<UserLoginRequest> {
        return BaseRequest("auth/login") {
            UserLoginRequest(
                correoElectronico = mail,
                telefono = phone,
                contrasenia = password,
                dispositivoId = platformUtils.androidId,
                idApp = ID_APP,
                marca = Build.BRAND ?: "GENERIC BRAND",
                modelo = Build.MODEL ?: "GENERIC MODEL",
                operario = OPERARIO_MOBILE,
                sistemaOperativo = ANDROID_OS,
            )
        }
    }

    private fun getNullLoginRequestException(
        mail: String = "",
        phone: String = "",
    ): IllegalArgumentException {
        return when {
            mail.isBlank() && phone.isBlank() -> MSG_BOTH_CREDENTIALS_BLANK
            mail.isNotBlank() && phone.isNotBlank() -> MSG_BOTH_CREDENTIALS_PROVIDED
            else -> "Unknown error"
        }.let {
            IllegalArgumentException(it)
        }
    }

    companion object {
        private const val ID_APP = 1
        private const val ANDROID_OS = 1
        private const val OPERARIO_MOBILE = "Mobile"
        private const val MSG_BOTH_CREDENTIALS_BLANK = "Any of [mail] or [phone] should be specified. Both are empty."
        private const val MSG_BOTH_CREDENTIALS_PROVIDED = "Just one of [mail] or [phone] could be specified."
    }
}

private object DefaultUserLoginApiCredentials : UserLoginApiCredentials("", "") {

    override val user: String
        get() = B_U()

    override val password: String
        get() = B_P()

    init {
      //  System.loadLibrary("constantes")
    }

    @Suppress("FunctionNaming")
    private external fun B_U(): String

    @Suppress("FunctionNaming")
    private external fun B_P(): String
}
