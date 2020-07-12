package com.studyfork.sfoide

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.studyfork.sfoide.util.ContextHelper
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberLogTree())

        if (BuildConfig.DEBUG) {
            SoLoader.init(this, false)

            if (FlipperUtils.shouldEnableFlipper(this)) {
                val client = AndroidFlipperClient.getInstance(this)
                client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
                client.addPlugin(NavigationFlipperPlugin.getInstance())
                client.addPlugin(NetworkFlipperPlugin())
                client.addPlugin(DatabasesFlipperPlugin(this))
                client.start()
            }
        }

        ContextHelper.context = applicationContext
    }
}

class TimberLogTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(
                priority,
                "[${ContextHelper.context?.getString(R.string.app_name)}-${BuildConfig.VERSION_NAME}]",
                message,
                t
        )
    }
}