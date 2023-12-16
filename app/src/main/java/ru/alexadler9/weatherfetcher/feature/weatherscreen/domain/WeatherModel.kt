package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain

data class WeatherModel(
    val datetime: String = "",
    val temp: String = "",
    val tempFeelsLike: String = "",
    val humidity: String = "",
    val windSpeed: String = "",
    val details: List<WeatherDetailsModel> = emptyList()
)