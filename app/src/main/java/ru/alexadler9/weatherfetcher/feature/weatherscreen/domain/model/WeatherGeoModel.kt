package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class WeatherGeoModel(
    val latitude: Double,
    val longitude: Double,
    val country: String
)