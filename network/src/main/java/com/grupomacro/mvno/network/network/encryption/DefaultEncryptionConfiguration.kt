package com.grupomacro.mvno.network.network.encryption

import com.grupomacro.mvno.network.network.encryption.data.EncryptionConfiguration


object DefaultEncryptionConfiguration {

    @JvmStatic
    val value = EncryptionConfiguration(
        algorithm = A_E_D(),
        transformation = A_T(),
        charset = A_C_N(),
        keyString = A_K()
    )

    init {
        System.loadLibrary("constantes")
    }

    private external fun A_E_D(): String
    private external fun A_T(): String
    private external fun A_C_N(): String
    private external fun A_K(): String
}
