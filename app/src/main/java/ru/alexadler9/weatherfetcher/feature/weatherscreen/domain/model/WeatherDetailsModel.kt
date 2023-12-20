package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class WeatherDetailsModel(
    /** Weather condition id. */
    val id: Int,

    /** Group of weather parameters (Rain, Snow, Clouds etc.). */
    val main: String,

    /** Weather condition within the group. */
    val description: String,

    /** Weather icon id. */
    val icon: String
)