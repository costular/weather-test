package com.costular.weathertest.data.model

import com.google.gson.annotations.SerializedName

data class ForecastEntity(
    @SerializedName("dt")
    val timestamp: Long,
    val main: ForecastMainEntity,
    val weather: ForecastWeatherEntity
) {

    fun getTemperature(): Float = main.temperature

    fun getMaxTemperature(): Float = main.maxTemperature

    fun getMinTemperature(): Float = main.minTemperature

    fun getWeatherTitle(): String = weather.main

    fun getWeatherDescription(): String = weather.description

    fun getWeatherIcon(): String = weather.icon

}