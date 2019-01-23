package com.costular.weathertest.domain.repository

import com.costular.weathertest.domain.model.Response
import io.reactivex.Single

interface WeatherRepository {

    fun getWeatherByCityName(cityName: String): Single<Response>

}