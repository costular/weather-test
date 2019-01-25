package com.costular.weathertest.data.model.mapper

import com.costular.weathertest.data.model.ForecastEntity
import com.costular.weathertest.data.model.ResponseEntity
import com.costular.weathertest.domain.model.*
import org.joda.time.DateTime
import org.joda.time.DateTimeFieldType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseMapper @Inject constructor(): Mapper<ResponseEntity, Response>{

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
            calculateDayWeatherByResponse(input.forecasts)
        )

    private fun calculateDayWeatherByResponse(list: List<ForecastEntity>): List<DayWeather> {
        val newList = list.map {
            Forecast(
                DateTime(it.timestamp * 1000L),
                it.getTemperature(),
                it.getMaxTemperature(),
                it.getMinTemperature(),
                it.getWeatherTitle(),
                it.getWeatherDescription(),
                it.getWeatherIcon()
            )
        }

        return newList.asSequence().groupBy { it.dateTime[DateTimeFieldType.dayOfMonth()] }
            .map { entry ->
                DayWeather(
                    entry.value.maxBy { it.temperatureMax }?.temperatureMax,
                    entry.value.minBy { it.temperatureMin }?.temperatureMin,
                    entry.value.first().dateTime.toLocalDate(),
                    entry.value
                )
            }.toList()
    }

    override fun transformList(inputList: List<ResponseEntity>): List<Response> =
        inputList.map { transform(it) }

}