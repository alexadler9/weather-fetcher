package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource

class WeatherRepositoryImpl(private val weatherRemoteSource: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun getTemperature(): String {
        return weatherRemoteSource.getWeather().main.temperature
    }
}