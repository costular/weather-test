package com.costular.weathertest.data.model

import com.google.gson.annotations.SerializedName

data class CityEntity(
    val id: Long,
    val name: String,
    @SerializedName("coord")
    val coordinates: CoordinatesEntity,
    val country: String
)