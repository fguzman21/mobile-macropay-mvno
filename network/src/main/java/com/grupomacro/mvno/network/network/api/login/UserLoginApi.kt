package com.grupomacro.mvno.network.network.api.login

import com.grupomacro.mvno.network.network.api.login.model.request.UserLoginRequest
import com.grupomacro.mvno.network.network.api.login.model.response.UserLoginResponse
import com.grupomacro.mvno.network.network.api.shared.AUTHORIZATION
import com.grupomacro.mvno.network.network.api.shared.model.request.BaseRequest
import com.grupomacro.mvno.network.network.api.shared.model.response.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserLoginApi {

    @POST(USER_LOGIN)
    suspend fun login(
        @Body
        request: BaseRequest<UserLoginRequest>,
        @Header(AUTHORIZATION)
        authorization: String
    ): Response<BaseResponse<UserLoginResponse>>

    companion object {
        private const val USER_LOGIN = "auth/login"
    }
}
