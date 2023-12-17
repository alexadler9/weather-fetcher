package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class ForecastModel(
    val weather: List<ForecastWeatherModel> = emptyList()
)
