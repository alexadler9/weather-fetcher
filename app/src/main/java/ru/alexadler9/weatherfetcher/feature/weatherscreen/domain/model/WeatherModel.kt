package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class WeatherModel(
    /** Date and time of weather calculation (dd.MM.yyyy HH:mm). */
    val datetime: String = "",

    /** Temperature. */
    val temp: String = "",

    /** Temperature. This temperature parameter accounts for the human perception of weather. */
    val tempFeelsLike: String = "",

    /** Humidity. */
    val humidity: String = "",

    /** Wind speed. */
    val windSpeed: String = "",

    /** List of weather conditions data. */
    val details: List<WeatherDetailsModel> = emptyList()
)