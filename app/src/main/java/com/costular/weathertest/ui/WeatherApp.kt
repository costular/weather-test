package com.costular.weathertest.ui

import android.app.Application
import com.costular.weathertest.ui.di.components.DaggerAppComponent
import com.costular.weathertest.ui.di.modules.AppModule
import com.costular.weathertest.ui.di.modules.DataModule
import com.costular.weathertest.ui.di.modules.NetModule
import net.danlew.android.joda.BuildConfig
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class WeatherApp : Application() {

    val component by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .dataModule(DataModule())
            .netModule(NetModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}