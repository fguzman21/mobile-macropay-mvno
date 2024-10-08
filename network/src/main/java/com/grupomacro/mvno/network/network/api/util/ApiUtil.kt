package com.grupomacro.mvno.network.network.api.util

import com.grupomacro.mvno.network.network.api.shared.model.response.ApiResponse
import com.grupomacro.mvno.network.network.api.shared.model.response.BaseResponse
import retrofit2.Response
import java.net.HttpURLConnection


private const val TAG = "ApiUtil"
private const val MSG_EMPTY_BODY = "Empty body response"

@Suppress("TooGenericExceptionCaught")
suspend fun <T> apiCallAsResult(
    apiCall: suspend () -> Response<BaseResponse<T>>
): Result<ApiResponse<T>> {
    return try {
        val httpResponse = apiCall()
        val dataResponse = httpResponse.body() ?: throw IllegalStateException(MSG_EMPTY_BODY)
        Result.success(ApiResponse(dataResponse, ApiResponse.HttpData(httpResponse)))
    } catch (e: Exception) {
        //Log.e(TAG, e.stackTraceToString())
        Result.failure(e)
    }
}

@Suppress("TooGenericExceptionCaught")
suspend fun <T : Any> retryOnUnauthorized(
    refreshToken: suspend () -> String,
    request: suspend () -> Response<T>
): Response<T> {
    return try {
        val response = request()
        when (response.code()) {
            HttpURLConnection.HTTP_UNAUTHORIZED,
            HttpURLConnection.HTTP_FORBIDDEN -> {
                refreshToken()
                request()
            }

            else -> {
                response
            }
        }
    } catch (e: Exception) {
        //Log.e(TAG, e.stackTraceToString())
        request()
    }
}
