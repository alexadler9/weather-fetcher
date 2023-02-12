package com.example.weatherfetcher.feature.weather_screen

import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

class WeatherInteractor(private val weatherRepo: WeatherRepo) {

    suspend fun getWeather(): WeatherModel {
        return weatherRepo.getTemperature()
    }
}