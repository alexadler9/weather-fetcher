package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class WeatherScreenPresenter(private val interactor: WeatherInteractor) {

    suspend fun getWeather(): String {
        return interactor.getWeather()
    }
}