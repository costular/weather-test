package com.costular.weathertest.domain.model

import org.joda.time.DateTime

data class Forecast(
    val dateTime: DateTime,
    val temperature: Int,
    val temperatureMax: Int,
    val temperatureMin: Int,
    val mainWeather: String,
    val mainWeatherDescription: String,
    val icon: String
)