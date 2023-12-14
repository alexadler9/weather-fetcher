package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository

class WeatherInteractor(private val weatherRepo: WeatherRepository) {

    suspend fun getWeather(): String {
        return weatherRepo.getTemperature()
    }
}