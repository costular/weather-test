package com.costular.weathertest.ui.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.costular.weathertest.R
import com.costular.weathertest.domain.model.Forecast
import com.costular.weathertest.util.DateUtils
import com.costular.weathertest.util.WeatherIconUtils
import kotlinx.android.synthetic.main.item_forecast_child.view.*

class ForecastChildAdapter
    : ListAdapter<Forecast, ForecastChildAdapter.ForecastChildViewHolder>(ForecastChildDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast_child, parent, false)
        return ForecastChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastChildViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ForecastChildViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(forecast: Forecast) {
            with (itemView) {
                textForecastTime.text = DateUtils.formatTime(forecast.dateTime)
                textForecastTemperature.text = "${forecast.temperature}ºC"

                Glide.with(context)
                    .load(WeatherIconUtils.getWeatherIconUrl(forecast.icon))
                    .into(imageForecastIcon)
            }
        }

    }
}