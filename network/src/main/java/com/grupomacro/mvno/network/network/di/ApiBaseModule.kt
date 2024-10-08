package com.grupomacro.mvno.network.network.di

import com.google.gson.GsonBuilder
import com.grupomacro.mvno.network.network.encryption.interceptor.RequestEncryptionInterceptor
import com.grupomacro.mvno.network.network.encryption.interceptor.ResponseDecryptionInterceptor
import com.grupomacro.mvno.network.network.mockapi.CustomDispatcher
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
internal class ApiBaseModule {

    @Provides
    @Named("RetrofitBuilderBasic")
    fun getRetrofitBuilderBasic(): Retrofit.Builder {
        return Retrofit.Builder()
            .client(getOkHttpClientBasic())
            .addConverterFactory(getGsonConverterFactory())
    }

    @Provides
    @Named("RetrofitBuilderFullEncrypted")
    fun getRetrofitBuilderFullEncrypted(): Retrofit.Builder {
        return Retrofit.Builder()
            .client(getOkHttpClientFullEncrypted())
            .addConverterFactory(getGsonConverterFactory())
    }

    @Singleton
    @Provides
    fun getMockWebServer(): MockWebServer {
        return MockWebServer().apply {
            dispatcher = CustomDispatcher()
        }
    }

    private fun getOkHttpClientBasic() = getOkHttpClient(isEncryptedRequest = false, isEncryptedResponse = false)

    private fun getOkHttpClientFullEncrypted() = getOkHttpClient(isEncryptedRequest = true, isEncryptedResponse = true)

    private fun getOkHttpClient(isEncryptedRequest: Boolean, isEncryptedResponse: Boolean) = buildOkHttpClient {
        val logginInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        addInterceptor(logginInterceptor)
        if (isEncryptedRequest) addInterceptor(RequestEncryptionInterceptor())
        if (isEncryptedResponse) addInterceptor(ResponseDecryptionInterceptor())
    }

    private fun getGsonConverterFactory() = GsonBuilder().setLenient().create().let { parser ->
        GsonConverterFactory.create(parser)
    }

    private fun buildOkHttpClient(configuration: OkHttpClient.Builder.() -> Unit): OkHttpClient {
        return OkHttpClient.Builder().apply(configuration).build()
    }
}
