package com.costular.weathertest.ui.forecast

import androidx.recyclerview.widget.DiffUtil
import com.costular.weathertest.domain.model.Forecast

class ForecastChildDiffUtil : DiffUtil.ItemCallback<Forecast>() {

    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean =
        oldItem.dateTime == newItem.dateTime

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean =
        oldItem == newItem

}