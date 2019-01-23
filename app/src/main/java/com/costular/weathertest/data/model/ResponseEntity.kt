package com.costular.weathertest.data.model

import com.google.gson.annotations.SerializedName

data class ResponseEntity(
    val city: CityEntity,
    @SerializedName("list")
    val forecasts: List<ForecastEntity>
)