package com.grupomacro.mvno.network.network.di

import android.content.Context
import com.grupomacro.mvno.network.constants.ConstantsProvider
import com.grupomacro.mvno.network.constants.impl.ConstantsProviderImpl
import com.grupomacro.mvno.network.network.platform.PlatformUtils
import com.grupomacro.mvno.network.network.platform.impl.PlatformUtilsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
internal class ConstantsProviderModule {

    @Singleton
    @Provides
    fun getConstantsProvider(): ConstantsProvider {
        return ConstantsProviderImpl()
    }

    @Singleton
    @Provides
    fun getPlatformUtils(context: Context): PlatformUtils {
        return PlatformUtilsImpl(context)
    }
}
