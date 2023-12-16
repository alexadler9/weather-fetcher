package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain

import ru.alexadler9.weatherfetcher.base.Either
import ru.alexadler9.weatherfetcher.base.attempt
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherGeoModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

class WeatherInteractor(private val weatherRepo: WeatherRepository) {

    suspend fun getWeather(latitude: String, longitude: String): Either<Throwable, WeatherModel> {
        return attempt { weatherRepo.getWeather(latitude, longitude) }
    }

    suspend fun getCoordinates(city: String): Either<Throwable, List<WeatherGeoModel>> {
        return attempt { weatherRepo.getCoordinates(city) }
    }
}