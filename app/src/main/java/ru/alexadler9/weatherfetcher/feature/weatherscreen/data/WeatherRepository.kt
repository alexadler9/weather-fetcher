package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.ForecastModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.GeoModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

/**
 * Repository for working with weather data.
 */
interface WeatherRepository {

    /**
     * Get current weather data for any location.
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
    suspend fun getWeather(latitude: String, longitude: String): WeatherModel

    /**
     * Get a 3-day forecast for any location.
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
    suspend fun getForecast(latitude: String, longitude: String): ForecastModel

    /**
     * Get coordinates for a given geographical name.
     * Can return more than one element if the specified geographical name is present in multiple countries.
     * @param city City name, state code (only for the US) and country code divided by comma.
     */
    suspend fun getCoordinates(city: String): List<GeoModel>
}