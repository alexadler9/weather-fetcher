package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherGeoModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

class WeatherRepositoryImpl(private val weatherRemoteSource: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun getWeather(latitude: String, longitude: String): WeatherModel {
        return weatherRemoteSource.getWeather(latitude, longitude).toDomain()
    }

    override suspend fun getCoordinates(city: String): List<WeatherGeoModel> {
        return weatherRemoteSource.getCoordinates(city).map {
            it.toDomain()
        }
    }
}