package com.costular.weathertest.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.costular.weathertest.R
import com.costular.weathertest.domain.model.DayWeather
import com.costular.weathertest.domain.model.Forecast
import com.costular.weathertest.util.DateUtils
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastAdapter : ListAdapter<DayWeather, ForecastAdapter.ForecastViewHolder>(ForecastDiffUtil()) {

    interface Callbacks {
        fun onDateClicked(dayWeather: DayWeather)
        fun onTimeClicked(forecast: Forecast)
    }

    private val recyclerPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val dayWeather = getItem(position)
        holder.bind(dayWeather)

        with (holder.itemView) {
            with (recyclerTimeForecasts) {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false).apply {
                    initialPrefetchItemCount = 6
                }
                itemAnimator = DefaultItemAnimator()
                adapter = ForecastChildAdapter().apply {
                    submitList(dayWeather.timeForecasts)
                }
                setRecycledViewPool(recyclerPool)
            }
        }
    }

    inner class ForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(dayWeather: DayWeather) {
            with (itemView) {
                textDayWeatherDate.text = DateUtils.formatDate(dayWeather.date)
                textForecastMaxTemp.text = "${dayWeather.maxTemperature}ºC"
                textForecastMinTemp.text = "${dayWeather.minTemperature}ºC"
            }
        }
    }
}