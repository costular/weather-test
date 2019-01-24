package com.costular.weathertest.data.repository.datasource

import com.costular.weathertest.data.model.mapper.ResponseMapper
import com.costular.weathertest.domain.model.Response
import io.reactivex.Single
import javax.inject.Inject

interface WeatherDataSource {

    fun getWeatherByCityName(cityName: String): Single<Response>

}