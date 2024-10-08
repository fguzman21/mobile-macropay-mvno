package com.grupomacro.mvno.network.network.api.shared.model.response

import retrofit2.Response

data class ApiResponse<T>(
    val response: BaseResponse<T>,
    val httpData: HttpData,
) {
    data class HttpData(
        val code: Int,
        val isSuccessful: Boolean,
        val errorBody: String?,
    ) {
        constructor(response: Response<*>) : this(
            response.code(),
            response.isSuccessful,
            response.errorBody()?.string()
        )
    }
}
