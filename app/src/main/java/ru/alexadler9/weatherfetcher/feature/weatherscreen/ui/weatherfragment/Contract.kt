package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

sealed class State {
    object Load : State()
    data class Content(val weatherModel: WeatherModel) : State()
    data class Error(val throwable: Throwable) : State()
    object NotFound : State()
}

data class ViewState(
    val state: State
)

sealed class UiEvent() : Event {
    object OnButtonClicked : UiEvent()
}

sealed class DataEvent() : Event {
    data class OnWeatherLoadSucceed(val weatherModel: WeatherModel) : DataEvent()
    data class OnWeatherLoadFailed(val error: Throwable) : DataEvent()
    object OnWeatherNotFound : DataEvent()
}