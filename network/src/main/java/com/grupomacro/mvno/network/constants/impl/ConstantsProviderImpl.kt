package com.grupomacro.mvno.network.constants.impl

import com.grupomacro.mvno.network.constants.ConstantsProvider

internal class ConstantsProviderImpl : ConstantsProvider {

    override val BASE_URL: String
        get() = UD()

    private external fun UD(): String

   /* companion object {
        init {
            System.loadLibrary("constantes")
        }
    }*/
}
