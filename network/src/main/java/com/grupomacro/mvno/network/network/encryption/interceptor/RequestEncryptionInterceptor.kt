package com.grupomacro.mvno.network.network.encryption.interceptor

import com.google.gson.Gson
import com.grupomacro.mvno.network.network.encryption.DefaultEncryptionConfiguration
import com.grupomacro.mvno.network.network.encryption.algorithm.EncryptionUtil
import com.grupomacro.mvno.network.network.encryption.data.EncryptedContent
import com.grupomacro.mvno.network.network.encryption.data.EncryptionConfiguration
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import kotlin.jvm.Throws

class RequestEncryptionInterceptor(
    private val configuration: EncryptionConfiguration = DefaultEncryptionConfiguration.value
) : Interceptor {

    private val encryptedUrls = mutableListOf<String>().apply {
        //add here encripted paths
    }

    private val encryptionUtil = EncryptionUtil()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if (!chain.request().canProceedToEncryption()) {
                return chain.proceed(chain.request())
            }
            val originalRequest = chain.request()
            val encryptedBody = encryptionUtil
                .encryptPKCS5(originalRequest.readBodyContent(), configuration)
                .toJson()
                .toRequestBody(originalRequest.body?.contentType())
            val encryptedRequest = originalRequest.newBuilder().post(encryptedBody).build()
            return chain.proceed(encryptedRequest)
        } catch (e: IOException) {
            throw e
        } catch (e: Exception) {
            throw IOException(e)
        }
    }

    private fun Request.canProceedToEncryption(): Boolean {
        val url = this.url.toString()
        val isEncryptionRequired = encryptedUrls.any { servicePath -> url.contains(servicePath) }
        val hasBody = this.body != null
        return isEncryptionRequired && hasBody
    }

    private fun Request.readBodyContent(): String {
        val copyRequest = this.newBuilder().build()
        val buffer = Buffer()
        requireNotNull(copyRequest.body).writeTo(buffer)
        return buffer.readUtf8()
    }

    private fun EncryptedContent.toJson(): String {
        /*val map = mapOf(
            "iv" to this.iv,
            "encrypted" to this.encrypted
        )*/
        return Gson().toJson(this)
    }
}
