package com.costular.weathertest.ui.forecast

import androidx.recyclerview.widget.DiffUtil
import com.costular.weathertest.domain.model.DayWeather

class ForecastDiffUtil : DiffUtil.ItemCallback<DayWeather>() {

    override fun areItemsTheSame(oldItem: DayWeather, newItem: DayWeather): Boolean =
        oldItem.date == newItem.date

    override fun areContentsTheSame(oldItem: DayWeather, newItem: DayWeather): Boolean =
        oldItem == newItem
}