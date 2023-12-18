package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain

import ru.alexadler9.weatherfetcher.base.Either
import ru.alexadler9.weatherfetcher.base.attempt
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.ForecastModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.GeoModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

class WeatherInteractor(
    private val weatherRepo: WeatherRepository,
    private val preferencesRepository: PreferencesRepository
) {

    suspend fun getWeather(latitude: String, longitude: String): Either<Throwable, WeatherModel> {
        return attempt { weatherRepo.getWeather(latitude, longitude) }
    }

    suspend fun getForecast(latitude: String, longitude: String): Either<Throwable, ForecastModel> {
        return attempt { weatherRepo.getForecast(latitude, longitude) }
    }

    suspend fun getCoordinates(city: String): Either<Throwable, List<GeoModel>> {
        return attempt { weatherRepo.getCoordinates(city) }
    }

    fun getCity() = preferencesRepository.getCity()

    fun setCity(city: String) {
        preferencesRepository.setCity(city)
    }
}