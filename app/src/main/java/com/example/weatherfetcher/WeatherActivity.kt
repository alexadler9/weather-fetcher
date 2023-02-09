package com.example.weatherfetcher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class WeatherActivity : AppCompatActivity() {

    private val presenter = WeatherPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        tvTemperature.text = presenter.getWeather(UUID.randomUUID().toString())
    }
}