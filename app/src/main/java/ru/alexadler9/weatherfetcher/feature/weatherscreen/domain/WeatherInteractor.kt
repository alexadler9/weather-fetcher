package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain

import ru.alexadler9.weatherfetcher.base.Either
import ru.alexadler9.weatherfetcher.base.attempt
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository

class WeatherInteractor(private val weatherRepo: WeatherRepository) {

    suspend fun getWeather(): Either<Throwable, WeatherModel> {
        return attempt { weatherRepo.getTemperature() }
    }
}