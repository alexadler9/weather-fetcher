package com.example.weatherfetcher.feature.weather_screen.data

interface WeatherRepo {
    suspend fun getTemperature(): String
}