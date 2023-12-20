package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.GeoRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel

/**
 * Source for accessing the remote weather API.
 */
class WeatherRemoteSource(private val api: WeatherApi) {

    /**
     * Get current weather data for any location.
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
    suspend fun getWeather(latitude: String, longitude: String): WeatherRemoteModel {
        return api.getWeather(
            lat = latitude,
            lon = longitude
        )
    }

    /**
     * Get a 5-day forecast including weather data with 3-hour step.
     * @param latitude Latitude.
     * @param longitude Longitude.
     */
    suspend fun getForecast(latitude: String, longitude: String): ForecastRemoteModel {
        return api.getForecast(
            lat = latitude,
            lon = longitude
        )
    }

    /**
     * Get coordinates for a given geographical name.
     * Can return more than one element if the specified geographical name is present in multiple countries.
     * @param city City name, state code (only for the US) and country code divided by comma.
     */
    suspend fun getCoordinates(city: String): List<GeoRemoteModel> {
        return api.getCoordinates(city)
    }
}