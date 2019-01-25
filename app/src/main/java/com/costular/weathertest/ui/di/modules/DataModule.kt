package com.costular.weathertest.ui.di.modules

import com.costular.weathertest.data.repository.WeatherRepositoryImpl
import com.costular.weathertest.data.repository.datasource.WeatherApiDataSource
import com.costular.weathertest.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun weatherRepository(weatherApiDataSource: WeatherApiDataSource): WeatherRepository =
        WeatherRepositoryImpl(weatherApiDataSource)

}