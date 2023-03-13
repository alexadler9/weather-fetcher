package com.example.weatherfetcher.feature.weather_screen

import com.example.weatherfetcher.base.Either
import com.example.weatherfetcher.base.attempt
import com.example.weatherfetcher.feature.data.WeatherRepo
import com.example.weatherfetcher.feature.domain.WeatherModel

class WeatherInteractor(private val weatherRepo: WeatherRepo) {

    suspend fun getWeather(): Either<Throwable, WeatherModel> {
        return attempt { weatherRepo.getWeather() }
    }
}