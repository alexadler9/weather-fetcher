package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.Event

data class ViewState(
    val isLoading: Boolean,
    val temperature: String
)

sealed class UiEvent() : Event {
    object OnButtonClicked : UiEvent()
}

sealed class DataEvent() : Event {
    data class OnWeatherFetchSucceed(val temperature: String) : DataEvent()
    data class OnWeatherFetchFailed(val error: Throwable) : DataEvent()
}