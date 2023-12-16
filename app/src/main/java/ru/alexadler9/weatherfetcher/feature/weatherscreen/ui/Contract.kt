package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherGeoModel
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
    data class OnCoordinatesLoadSucceed(val coordinates: List<WeatherGeoModel>) : DataEvent()
    data class OnWeatherLoadSucceed(val weatherModel: WeatherModel) : DataEvent()
    data class OnDataLoadFailed(val error: Throwable) : DataEvent()
}