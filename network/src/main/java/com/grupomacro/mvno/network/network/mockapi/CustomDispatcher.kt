package com.grupomacro.mvno.network.network.mockapi

import com.grupomacro.mvno.network.network.mockapi.response.AUTH_LOGIN_PATH
import com.grupomacro.mvno.network.network.mockapi.response.AUTH_LOGIN_SUCCESS_RESPONSE
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class CustomDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        println("CustomDispatcher service: ${request.path}")
        return mockResponse {
            when (request.path) {
                AUTH_LOGIN_PATH -> setBody(AUTH_LOGIN_SUCCESS_RESPONSE)
                //INFO_CLIENTE_PATH -> setBody(INFO_CLIENTE_SUCCESS_RESPONSE)
                else -> this.setResponseCode(HttpURLConnection.HTTP_NOT_IMPLEMENTED)
            }
            setBodyDelay(RESPONSE_DELAY, TimeUnit.MILLISECONDS)
        }
    }

    private fun mockResponse(body: MockResponse.() -> Unit): MockResponse {
        return MockResponse().apply(body)
    }

    companion object {
        private const val RESPONSE_DELAY = 2_000L
    }
}
