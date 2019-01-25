package com.costular.weathertest.domain.model

import org.joda.time.LocalDate

data class DayWeather(
    val maxTemperature: Int?,
    val minTemperature: Int?,
    val date: LocalDate,
    val timeForecasts: List<Forecast>
)