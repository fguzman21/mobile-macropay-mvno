package com.grupomacro.mvno.network.network.di

import android.content.Context
import com.grupomacro.mvno.network.ApiFacadeEntryPoint
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ConstantsProviderModule::class,
        ApiBaseModule::class,
        UserLoginApiModule::class
        /*UserFinancingApiModule::class,
        UserRegistrationApiModule::class,
        PaymentCodiApiModule::class,*/
    ]
)
interface MainNetworkComponent {

    fun inject(apiFacadeEntryPoint: ApiFacadeEntryPoint)

    @Component.Builder
    interface Builder {

        fun build(): MainNetworkComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}
