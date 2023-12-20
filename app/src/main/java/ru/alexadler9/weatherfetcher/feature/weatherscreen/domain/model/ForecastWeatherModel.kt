package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class ForecastWeatherModel(
    /** Day and month of weather calculation (for example "1 дек."). */
    val date: String = "",

    /** Day of the week (for example "ПН"). */
    val dayOfWeek: String = "",

    /** Day temperature. */
    val tempDay: String = "",

    /** Night temperature. */
    val tempNight: String = "",

    /** Humidity. */
    val humidity: String = "",

    /** Wind speed. */
    val windSpeed: String = "",

    /** List of weather conditions data. */
    val details: List<WeatherDetailsModel> = emptyList()
)
