package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import ru.alexadler9.weatherfetcher.base.Event

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