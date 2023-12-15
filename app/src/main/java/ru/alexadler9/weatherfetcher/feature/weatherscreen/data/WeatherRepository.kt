package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherModel

interface WeatherRepository {
    suspend fun getTemperature(): WeatherModel
}