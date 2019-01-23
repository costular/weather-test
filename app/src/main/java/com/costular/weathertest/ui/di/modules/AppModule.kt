package com.costular.weathertest.ui.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun application(): Application = application

    @Provides
    fun context(): Context = application.applicationContext

}