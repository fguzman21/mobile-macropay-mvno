package com.grupomacro.mvno.network

import android.content.Context
import com.grupomacro.mvno.network.constants.ConstantsProvider
import com.grupomacro.mvno.network.network.api.login.UserLoginApiFacade
import com.grupomacro.mvno.network.network.di.DaggerMainNetworkComponent

import javax.inject.Inject

class ApiFacadeEntryPoint private constructor() {

    @Inject
    lateinit var userLoginApiFacade: UserLoginApiFacade

    /*@Inject
    lateinit var userRegistrationApiFacade: UserRegistrationApiFacade

    @Inject
    lateinit var paymentCodiApiFacade: PaymentCodiApiFacade

    @Inject
    lateinit var userFinancingApiFacade: UserFinancingApiFacade*/

    //TODO("borrar si ya no se usa")
    @Inject
    lateinit var constantsProvider: ConstantsProvider

    companion object {

        val instance = ApiFacadeEntryPoint()

        fun initialize(context: Context) {
            DaggerMainNetworkComponent
                .builder()
                .context(context)
                .build()
                .inject(instance)
        }
    }
}
