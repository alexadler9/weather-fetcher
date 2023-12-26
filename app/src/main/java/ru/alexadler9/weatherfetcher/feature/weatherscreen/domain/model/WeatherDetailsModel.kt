package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class WeatherDetailsModel(
    /** Weather condition within the group. */
    val description: String,

    /** Weather icon url. */
    val icon: String
)