package com.costular.weathertest.data.network

import com.costular.weathertest.data.model.ResponseEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    fun getWeatherForecastsByCityName(@Query("q") cityName: String): Single<ResponseEntity>

}