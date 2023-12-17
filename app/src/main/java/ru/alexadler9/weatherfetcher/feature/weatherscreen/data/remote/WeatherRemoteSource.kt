package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.GeoRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel

class WeatherRemoteSource(private val api: WeatherApi) {

    suspend fun getWeather(latitude: String, longitude: String): WeatherRemoteModel {
        return api.getWeather(
            lat = latitude,
            lon = longitude
        )
    }

    suspend fun getForecast(latitude: String, longitude: String): ForecastRemoteModel {
        return api.getForecast(
            lat = latitude,
            lon = longitude
        )
    }

    suspend fun getCoordinates(city: String): List<GeoRemoteModel> {
        return api.getCoordinates(city)
    }
}