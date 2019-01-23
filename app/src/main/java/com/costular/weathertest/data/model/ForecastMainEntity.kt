package com.costular.weathertest.data.model

import com.google.gson.annotations.SerializedName

data class ForecastMainEntity(
    @SerializedName("temp")
    val temperature: Float,
    @SerializedName("temp_min")
    val minTemperature: Float,
    @SerializedName("temp_max")
    val maxTemperature: Float
)