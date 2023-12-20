package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class ForecastModel(
    /** List of daily forecasts. */
    val weather: List<ForecastWeatherModel> = emptyList()
)
