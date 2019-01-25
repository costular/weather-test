package com.costular.weathertest.ui.di.components

import com.costular.weathertest.ui.di.modules.AppModule
import com.costular.weathertest.ui.di.modules.DataModule
import com.costular.weathertest.ui.di.modules.NetModule
import com.costular.weathertest.ui.di.modules.ViewModelModule
import com.costular.weathertest.ui.forecast.ForecastFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, NetModule::class, ViewModelModule::class])
interface AppComponent {

    // Expose dependencies


    fun inject(forecastFragment: ForecastFragment)

}