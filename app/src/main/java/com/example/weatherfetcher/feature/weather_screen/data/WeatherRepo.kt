package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

interface WeatherRepo {
    suspend fun getTemperature(): WeatherModel
}