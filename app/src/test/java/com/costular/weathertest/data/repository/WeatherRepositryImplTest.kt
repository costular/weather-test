package com.costular.weathertest.data.repository

import com.costular.weathertest.data.model.ModelFixtures
import com.costular.weathertest.data.repository.datasource.WeatherApiDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class WeatherRepositryImplTest {

    lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    val weatherApiDataSource: WeatherApiDataSource = mock {
        on { getWeatherByCityName("London") }.thenReturn(Single.just(ModelFixtures.createFakeResponse()))
    }

    @Before
    fun setUp() {
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherApiDataSource)
    }

    @Test
    fun `test retrieve weather by id`() {
        val result = weatherRepositoryImpl.getWeatherByCityName("London")

        verify(weatherApiDataSource).getWeatherByCityName("London")

        result
            .test()
            .assertValue { it.city.name == ModelFixtures.createFakeResponse().city.name }
            .assertNoErrors()
    }

}