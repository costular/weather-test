package com.costular.weathertest.ui

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }

}