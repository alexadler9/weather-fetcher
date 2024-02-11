package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import ru.alexadler9.weatherfetcher.base.Action
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherModel

sealed class State {
    object Load : State()
    data class Content(val weatherModel: WeatherModel) : State()
    data class Error(val throwable: Throwable) : State()
    object NotFound : State()
}

data class ViewState(
    val cityEditable: String,
    val city: String,
    val state: State
)

sealed class ViewEvent {
}

sealed class UiAction() : Action {
    data class OnCitySearchEdited(val text: String) : UiAction()
    object OnCitySearchButtonClicked : UiAction()
    object OnUpdateButtonClicked : UiAction()
}

sealed class DataAction() : Action {
    data class OnWeatherLoadSucceed(val weatherModel: WeatherModel) : DataAction()
    data class OnWeatherLoadFailed(val error: Throwable) : DataAction()
    object OnWeatherNotFound : DataAction()
}