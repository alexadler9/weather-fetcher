package com.example.weatherfetcher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnWeather = findViewById<Button>(R.id.btnWeather)
        btnWeather.setOnClickListener {
            Intent(this, WeatherActivity::class.java).also(::startActivity)
        }
    }
}