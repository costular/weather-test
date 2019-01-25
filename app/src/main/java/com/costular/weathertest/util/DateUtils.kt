package com.costular.weathertest.util

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

object DateUtils {

    val timeFormat = DateTimeFormat.forPattern("HH:mm")
    val dateFormat = DateTimeFormat.forPattern("d MMM")

    fun formatTime(dateTime: DateTime): String {
        return timeFormat.print(dateTime)
    }

    fun formatDate(localDate: LocalDate): String {
        return dateFormat.print(localDate)
    }

    fun formatDate(dateTime: DateTime): String {
        return dateFormat.print(dateTime)
    }
}