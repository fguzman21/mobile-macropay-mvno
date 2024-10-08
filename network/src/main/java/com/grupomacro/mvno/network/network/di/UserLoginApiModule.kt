package com.grupomacro.mvno.network.network.di

import com.grupomacro.mvno.network.network.api.login.UserLoginApi
import com.grupomacro.mvno.network.network.api.login.UserLoginApiFacade
import com.grupomacro.mvno.network.network.platform.PlatformUtils
import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal class UserLoginApiModule {

    private val isMockEnabled = true

    @Singleton
    @Provides
    fun getUserLoginApiFacade(userLoginApi: UserLoginApi, platformUtils: PlatformUtils): UserLoginApiFacade {
        return UserLoginApiFacade(userLoginApi, platformUtils)
    }

    @Singleton
    @Provides
    fun getUserLoginApi(
        @Named("url_login") url: String,
        @Named("RetrofitBuilderBasic") retrofitBuilder: Retrofit.Builder
    ): UserLoginApi {
        retrofitBuilder.baseUrl(url)
        return retrofitBuilder.build().create(UserLoginApi::class.java)
    }

    @Singleton
    @Provides
    @Named("url_login")
    fun getBaseUrl(mockWebServerProvider: Provider<MockWebServer>): String {
        return if (isMockEnabled) {
            mockWebServerProvider.get().url("").toString()
        } else {
            "https://aapiappdev.macropay.mx/"
        }
    }
}
