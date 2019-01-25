package com.costular.weathertest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.costular.weathertest.R
import com.costular.weathertest.ui.base.BaseActivity
import com.costular.weathertest.ui.forecast.ForecastFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar(false)

        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, ForecastFragment())
            .commit()
    }
}
