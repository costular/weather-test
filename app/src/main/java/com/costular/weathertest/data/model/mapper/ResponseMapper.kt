package com.costular.weathertest.data.model.mapper

import com.costular.weathertest.data.model.ResponseEntity
import com.costular.weathertest.domain.model.City
import com.costular.weathertest.domain.model.Coordinates
import com.costular.weathertest.domain.model.Forecast
import com.costular.weathertest.domain.model.Response
import org.joda.time.DateTime

class ResponseMapper : Mapper<ResponseEntity, Response> {

    override fun transform(input: ResponseEntity): Response =
        Response(
            City(
                input.city.id,
                input.city.name,
                Coordinates(
                    input.city.coordinates.lat,
                    input.city.coordinates.lon
                ),
                input.city.country
            ),
            input.forecasts.map {
                Forecast(
                    DateTime(it.timestamp),
                    it.getTemperature(),
                    it.getMaxTemperature(),
                    it.getMinTemperature(),
                    it.getWeatherTitle(),
                    it.getWeatherDescription(),
                    it.getWeatherIcon()
                )
            }
        )

    override fun transformList(inputList: List<ResponseEntity>): List<Response> =
            inputList.map { transform(it) }

}