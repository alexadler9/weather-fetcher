package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherGeoRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel

class WeatherRemoteSource(private val api: WeatherApi) {

    suspend fun getWeather(latitude: String, longitude: String): WeatherRemoteModel {
        return api.getWeather(
            lat = latitude,
            lon = longitude
        )
    }

    suspend fun getCoordinates(city: String): List<WeatherGeoRemoteModel> {
        return api.getCoordinates(city)
    }
}