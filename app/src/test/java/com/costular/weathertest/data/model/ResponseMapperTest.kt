package com.costular.weathertest.data.model

import com.costular.weathertest.data.model.mapper.ResponseMapper
import com.costular.weathertest.domain.model.Response
import org.assertj.core.api.Assertions
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

class ResponseMapperTest {

    lateinit var responseMapper: ResponseMapper

    @Before
    fun setUp() {
        responseMapper = ResponseMapper()
    }

    @Test
    fun `test transform response entity`() {
        val responseEntity = ModelFixtures.createFakeResponseEntity()

        val response = responseMapper.transform(responseEntity)

        Assertions.assertThat(response).isInstanceOf(Response::class.java)
        Assertions.assertThat(response.city.id).isEqualTo(responseEntity.city.id)
        Assertions.assertThat(response.city.countryCode).isEqualTo(responseEntity.city.country)

        Assertions.assertThat(response.weatherForecasts.first().dateTime)
            .isEqualTo(DateTime(responseEntity.forecasts.first().timestamp))

        Assertions.assertThat(response.weatherForecasts.first().temperature)
            .isEqualTo(responseEntity.forecasts.first().getTemperature())

        Assertions.assertThat(response.weatherForecasts.first().mainWeather)
            .isEqualTo(responseEntity.forecasts.first().getWeatherTitle())
    }

}