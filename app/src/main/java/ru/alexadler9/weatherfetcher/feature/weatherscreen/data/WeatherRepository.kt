package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

interface WeatherRepository {
    suspend fun getTemperature(): String
}