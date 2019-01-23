package com.costular.weathertest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.costular.weathertest.R
import com.costular.weathertest.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
