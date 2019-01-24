package com.costular.weathertest.data.model

import com.costular.weathertest.data.model.mapper.ResponseMapper
import com.costular.weathertest.domain.model.Response

object ModelFixtures {

    fun createFakeResponseEntity(): ResponseEntity =
        ResponseEntity(
            CityEntity(
                1,
                "London",
                CoordinatesEntity(
                    51.5073,
                    -0.1277
                ),
                "GB"
            ),
            listOf(
                ForecastEntity(
                    System.currentTimeMillis(),
                    ForecastMainEntity(
                        1.2f,
                        -2f,
                        5f
                    ),
                    ForecastWeatherEntity(
                        1,
                        "Rain",
                        "light rain",
                        "10d"
                    )
                )
            )
        )

    fun createFakeResponse(): Response = ResponseMapper().transform(createFakeResponseEntity())

}