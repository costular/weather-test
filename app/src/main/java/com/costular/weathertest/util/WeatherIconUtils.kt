package com.costular.weathertest.util

object WeatherIconUtils {

    fun getWeatherIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

}