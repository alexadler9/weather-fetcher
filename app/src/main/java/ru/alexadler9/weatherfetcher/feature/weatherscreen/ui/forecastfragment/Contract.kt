package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.ForecastModel

sealed class State {
    object Load : State()
    data class Content(val forecastModel: ForecastModel) : State()
    data class Error(val throwable: Throwable) : State()
    object NotFound : State()
}

data class ViewState(
    val cityEditable: String,
    val city: String,
    val state: State
)

sealed class UiEvent() : Event {
    data class OnCitySearchEdit(val text: String) : UiEvent()
    object OnCitySearchButtonClicked : UiEvent()
    object OnButtonClicked : UiEvent()
}

sealed class DataEvent() : Event {
    data class OnForecastLoadSucceed(val forecastModel: ForecastModel) : DataEvent()
    data class OnForecastLoadFailed(val error: Throwable) : DataEvent()
    object OnForecastNotFound : DataEvent()
}