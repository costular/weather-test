package com.costular.weathertest.ui.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.costular.weathertest.RxSchedulerRule
import com.costular.weathertest.data.PreferencesManager
import com.costular.weathertest.data.model.ModelFixtures
import com.costular.weathertest.domain.repository.WeatherRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ForecastViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule: RxSchedulerRule = RxSchedulerRule()

    lateinit var forecastViewModel: ForecastViewModel

    val weatherRepository: WeatherRepository = mock {
        on { getWeatherByCityName("London") }.thenReturn(Single.just(ModelFixtures.createFakeResponse()))
    }

    val preferencesManager: PreferencesManager = mock {
        on { currentCity }.thenReturn("London")
    }

    @Before
    fun setUp() {
        forecastViewModel = ForecastViewModel(weatherRepository, preferencesManager)
    }

    @Test
    fun `test view model retrieve default city from preferences`() {
        Assertions.assertThat(forecastViewModel.currentCity).isEqualTo("London")
    }

    @Test
    fun `test view model updates current city with a different argument`() {
        // Given
        val viewModel = Mockito.mock(ForecastViewModel::class.java)

        // When
        viewModel.loadWeatherByCityName("Barcelona")

        // Then
        verify(viewModel).currentCity = "Barcelona"
    }

    @Test
    fun `test view model retrieves forecast and pass to livedata`() {
        forecastViewModel.loadWeatherByCityName("London")

        verify(weatherRepository, times(2)).getWeatherByCityName("London")

        with (forecastViewModel.responseState.value) {
            requireNotNull(this!!)
            val response = ModelFixtures.createFakeResponse()

            Assertions.assertThat(city.id).isEqualTo(response.city.id)
            Assertions.assertThat(city.name).isEqualTo(response.city.name)
        }
    }

}