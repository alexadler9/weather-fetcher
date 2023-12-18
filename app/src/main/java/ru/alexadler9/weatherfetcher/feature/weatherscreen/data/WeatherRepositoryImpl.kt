package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource

class WeatherRepositoryImpl(private val weatherRemoteSource: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun getWeather(latitude: String, longitude: String) =
        weatherRemoteSource.getWeather(latitude, longitude).toDomain()

    override suspend fun getForecast(latitude: String, longitude: String) =
        weatherRemoteSource.getForecast(latitude, longitude).toDomain()

    override suspend fun getCoordinates(city: String) =
        weatherRemoteSource.getCoordinates(city).map {
            it.toDomain()
        }
}