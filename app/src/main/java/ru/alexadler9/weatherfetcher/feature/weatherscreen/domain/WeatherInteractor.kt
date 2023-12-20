package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain

import ru.alexadler9.weatherfetcher.base.Either
import ru.alexadler9.weatherfetcher.base.attempt
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.ForecastModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.GeoModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

/**
 * Weather interactor.
 */
class WeatherInteractor(
    private val weatherRepository: WeatherRepository,
    private val preferencesRepository: PreferencesRepository
) {

    /**
     * Get current weather data for any location.
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
    suspend fun getWeather(latitude: String, longitude: String): Either<Throwable, WeatherModel> {
        return attempt { weatherRepository.getWeather(latitude, longitude) }
    }

    /**
     * Get a 3-day forecast for any location.
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
    suspend fun getForecast(latitude: String, longitude: String): Either<Throwable, ForecastModel> {
        return attempt { weatherRepository.getForecast(latitude, longitude) }
    }

    /**
     * Get coordinates for a given geographical name.
     * Can return more than one element if the specified geographical name is present in multiple countries.
     * @param city City name, state code (only for the US) and country code divided by comma.
     */
    suspend fun getCoordinates(city: String): Either<Throwable, List<GeoModel>> {
        return attempt { weatherRepository.getCoordinates(city) }
    }

    /**
     * Get the last city specified by user.
     */
    fun getCity() = preferencesRepository.getCity()

    /**
     * Save the city.
     * @param city City name.
     */
    fun setCity(city: String) {
        preferencesRepository.setCity(city)
    }
}