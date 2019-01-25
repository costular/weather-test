package com.costular.weathertest.ui.forecast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.costular.weathertest.data.PreferencesManager
import com.costular.weathertest.domain.model.Response
import com.costular.weathertest.domain.repository.WeatherRepository
import com.costular.weathertest.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val weatherRepository: WeatherRepository,
                                            private val preferencesManager: PreferencesManager) : BaseViewModel() {

    val responseState = MutableLiveData<Response>()
    val isLoadingState = MutableLiveData<Boolean>()

    var currentCity: String = preferencesManager.currentCity

    init {
        loadWeatherByCityName(preferencesManager.currentCity)
    }

    fun loadWeatherByCityName(cityName: String) {
        if (currentCity != cityName) {
            currentCity = cityName
            preferencesManager.currentCity = cityName
        }

        weatherRepository.getWeatherByCityName(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoadingState.value = true }
            .doFinally { isLoadingState.value = false }
            .subscribeBy(
                onSuccess = {
                    responseState.value = it
                },
                onError = {
                    Timber.e(it)
                }
            ).addTo(disposables)
    }

}