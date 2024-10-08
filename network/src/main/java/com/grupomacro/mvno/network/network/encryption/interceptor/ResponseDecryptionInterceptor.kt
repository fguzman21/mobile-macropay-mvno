package com.grupomacro.mvno.network.network.encryption.interceptor

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.grupomacro.mvno.network.network.api.shared.model.response.BaseResponse
import com.grupomacro.mvno.network.network.encryption.DefaultEncryptionConfiguration
import com.grupomacro.mvno.network.network.encryption.algorithm.EncryptionUtil
import com.grupomacro.mvno.network.network.encryption.data.EncryptedContent
import com.grupomacro.mvno.network.network.encryption.data.EncryptionConfiguration
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import java.net.HttpURLConnection

class ResponseDecryptionInterceptor(
    private val configuration: EncryptionConfiguration = DefaultEncryptionConfiguration.value
) : Interceptor {

    private val encryptionUtil = EncryptionUtil()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val originalResponse = chain.proceed(chain.request())
            val clazz = chain.request().tag(Class::class.java)
            val encryptedData = originalResponse.getEncryptedData()
            if (clazz == null || encryptedData == null) {
                return originalResponse
            }
            val jsonDecrypted = encryptionUtil.decryptPCKS5(encryptedData, configuration)
            buildDecryptedResponse(originalResponse, jsonDecrypted, clazz)
        } catch (e: IOException) {
            throw e
        } catch (e: Exception) {
            throw IOException(e)
        }
    }

    private fun Response.getEncryptedData(): EncryptedContent? {
        if (this.code >= HttpURLConnection.HTTP_BAD_REQUEST) return null
        val jsonEncrypted = this.peekBody(Long.MAX_VALUE).string()
        val typeToken = object : TypeToken<BaseResponse<EncryptedContent>>() {}.type
        return Gson()
            .fromJson<BaseResponse<EncryptedContent>?>(jsonEncrypted, typeToken)
            .takeIf { baseResponse ->
                baseResponse.data != null// && baseResponse.success
            }?.data?.attributes
    }

    private fun buildDecryptedResponse(originalResponse: Response, jsonDecrypted: String, clazz: Class<*>): Response {
        val decryptedData = Gson().fromJson(jsonDecrypted, clazz)
        val decryptedBaseResponse = BaseResponse(
            //code = originalResponse.code,
            //success = originalResponse.isSuccessful,
            //message = originalResponse.message,
            data = BaseResponse.ResponseData("1", "encrypted", decryptedData),
            errors = null
        )
        val decryptedResponseBody = Gson()
            .toJson(decryptedBaseResponse)
            .toResponseBody(originalResponse.body?.contentType())
        return originalResponse.newBuilder()
            .code(originalResponse.code)
            .body(decryptedResponseBody)
            .build()
    }
}
