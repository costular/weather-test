package com.costular.weathertest.data.model

import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

data class ForecastEntity(
    @SerializedName("dt")
    val timestamp: Long,
    val main: ForecastMainEntity,
    val weather: List<ForecastWeatherEntity>
) {

    fun getTemperature(): Int = main.temperature.roundToInt()

    fun getMaxTemperature(): Int = main.maxTemperature.roundToInt()

    fun getMinTemperature(): Int = main.minTemperature.roundToInt()

    fun getWeatherTitle(): String = weather.first().main

    fun getWeatherDescription(): String = weather.first().description

    fun getWeatherIcon(): String = weather.first().icon

}