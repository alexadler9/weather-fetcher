package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel

class WeatherRemoteSource(private val api: WeatherApi) {

    // TODO add query
    suspend fun getWeather(): WeatherRemoteModel {
        return api.getWeather(query = "Chelyabinsk")
    }
}