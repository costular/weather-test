package com.costular.weathertest.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(private val context: Context) {

    var currentCity: String
    get() = getSharedPreferences().getString(PREF_CURRENT_CITY, "London")
    set(value) {
        getSharedPreferences()
            .edit()
            .putString(PREF_CURRENT_CITY, value)
            .apply()
    }

    fun getSharedPreferences(): SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    companion object {
        const val PREFERENCE_NAME = "costular-weather"

        const val PREF_CURRENT_CITY = "city"
    }

}