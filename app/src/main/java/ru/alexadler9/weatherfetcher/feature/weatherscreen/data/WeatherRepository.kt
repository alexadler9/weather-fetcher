package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherGeoModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

interface WeatherRepository {
    suspend fun getWeather(latitude: String, longitude: String): WeatherModel
    suspend fun getCoordinates(city: String): List<WeatherGeoModel>
}