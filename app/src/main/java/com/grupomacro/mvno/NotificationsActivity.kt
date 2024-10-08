package com.grupomacro.mvno

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.os.bundleOf


class NotificationsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {

        fun createBundleForSection(): Bundle {
            return bundleOf()
        }

        fun launch(launchingActivity: Activity, bundle: Bundle = Bundle()) {
            val intent = Intent(launchingActivity, NotificationsActivity::class.java).apply {
                putExtras(bundle)
            }
            launchingActivity.startActivity(intent)
        }
    }
}