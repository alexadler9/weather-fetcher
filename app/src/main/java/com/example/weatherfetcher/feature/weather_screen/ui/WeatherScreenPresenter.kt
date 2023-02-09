package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor

class WeatherScreenPresenter(private val interactor: WeatherInteractor) {

    suspend fun getWeather(): String {
        return interactor.getWeather()
    }
}