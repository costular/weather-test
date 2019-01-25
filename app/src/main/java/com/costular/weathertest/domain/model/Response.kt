package com.costular.weathertest.domain.model

data class Response(
    val city: City,
    val weatherForecasts: List<DayWeather>
)