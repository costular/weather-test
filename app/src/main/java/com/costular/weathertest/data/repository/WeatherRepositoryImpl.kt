package com.costular.weathertest.data.repository

import com.costular.weathertest.data.repository.datasource.WeatherApiDataSource
import com.costular.weathertest.domain.model.Response
import com.costular.weathertest.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiDataSource: WeatherApiDataSource
) : WeatherRepository {

    override fun getWeatherByCityName(cityName: String): Single<Response> =
            weatherApiDataSource.getWeatherByCityName(cityName)

}