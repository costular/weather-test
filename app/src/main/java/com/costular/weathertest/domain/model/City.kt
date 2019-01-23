package com.costular.weathertest.domain.model

data class City(
    val id: Long,
    val name: String,
    val coordinates: Coordinates,
    val countryCode: String
)