package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherModel

class WeatherRepositoryImpl(private val weatherRemoteSource: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun getTemperature(): WeatherModel {
        return weatherRemoteSource.getWeather().toDomain()
    }
}