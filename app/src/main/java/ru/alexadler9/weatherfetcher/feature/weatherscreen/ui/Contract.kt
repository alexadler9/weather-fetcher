package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherModel

sealed class State {
    object Load : State()
    data class Content(val weatherModel: WeatherModel) : State()
    data class Error(val throwable: Throwable) : State()
}

data class ViewState(
    val state: State
)

sealed class UiEvent() : Event {
    object OnButtonClicked : UiEvent()
}

sealed class DataEvent() : Event {
    data class OnWeatherFetchSucceed(val weatherModel: WeatherModel) : DataEvent()
    data class OnWeatherFetchFailed(val error: Throwable) : DataEvent()
}