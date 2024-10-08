package com.grupomacro.mvno.network.network.platform.impl

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.grupomacro.mvno.network.network.platform.PlatformUtils

@SuppressLint("HardwareIds")
class PlatformUtilsImpl(val context: Context) : PlatformUtils {

    override val androidId: String by lazy {
        val id = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        id ?: "ANDROID_ID"
    }
}
