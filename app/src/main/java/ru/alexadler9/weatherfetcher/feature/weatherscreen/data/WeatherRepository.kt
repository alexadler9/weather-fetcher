package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.ForecastModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.GeoModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

interface WeatherRepository {
    suspend fun getWeather(latitude: String, longitude: String): WeatherModel
    suspend fun getForecast(latitude: String, longitude: String): ForecastModel
    suspend fun getCoordinates(city: String): List<GeoModel>
}