package com.costular.weathertest.data.repository.datasource

import com.costular.weathertest.data.model.CityEntity
import com.costular.weathertest.data.model.CoordinatesEntity
import com.costular.weathertest.data.model.ResponseEntity
import com.costular.weathertest.data.model.mapper.ResponseMapper
import com.costular.weathertest.data.network.WeatherApi
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class WeatherApiDataSourceTest {

    val weatherApi: WeatherApi = mock()
    val responseMapper: ResponseMapper = ResponseMapper()

    lateinit var weatherApiDataSource: WeatherApiDataSource

    val fakeResponse = Single.just(
        ResponseEntity(
            CityEntity(1, "London", CoordinatesEntity(0.00, 0.00), "GB"),
            listOf()
        )
    )

    @Before
    fun setUp() {
        weatherApiDataSource = WeatherApiDataSource(weatherApi, responseMapper)
    }

    @Test
    fun `test get weather entity by id from api calls api service`() {
        whenever(weatherApi.getWeatherForecastsByCityName("London")).thenReturn(fakeResponse)

        weatherApiDataSource.getWeatherByCityName("London")
        verify(weatherApi).getWeatherForecastsByCityName("London")
    }

    @Test
    fun `test get weather entity by id from api retrieves right information`() {
        whenever(weatherApi.getWeatherForecastsByCityName("London")).thenReturn(fakeResponse)

        weatherApiDataSource.getWeatherByCityName("London")
            .test()
            .assertValue { it.city.name == "London" }
            .assertNoErrors()
    }

}