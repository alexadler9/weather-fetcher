package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain

data class WeatherDetailsModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
