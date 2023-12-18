package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class ForecastWeatherModel(
    val date: String = "",
    val dayOfWeek: String = "",
    val tempDay: String = "",
    val tempNight: String = "",
    val humidity: String = "",
    val windSpeed: String = "",
    val details: List<WeatherDetailsModel> = emptyList()
)
