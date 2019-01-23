package com.costular.weathertest.domain.model

import org.joda.time.DateTime

data class Forecast(
    val dateTime: DateTime,
    val temperature: Float,
    val temperatureMax: Float,
    val temperatureMix: Float,
    val mainWeather: String,
    val mainWeatherDescription: String,
    val icon: String
)