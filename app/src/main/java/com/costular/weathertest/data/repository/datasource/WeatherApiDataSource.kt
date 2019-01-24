package com.costular.weathertest.data.repository.datasource

import com.costular.weathertest.data.model.mapper.ResponseMapper
import com.costular.weathertest.data.network.WeatherApi
import com.costular.weathertest.domain.model.Response
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherApiDataSource @Inject constructor(
    private val weatherApi: WeatherApi,
    private val responseMapper: ResponseMapper
) : WeatherDataSource {

    override fun getWeatherByCityName(cityName: String): Single<Response> =
        weatherApi
            .getWeatherForecastsByCityName(cityName)
            .map { responseMapper.transform(it) }

}