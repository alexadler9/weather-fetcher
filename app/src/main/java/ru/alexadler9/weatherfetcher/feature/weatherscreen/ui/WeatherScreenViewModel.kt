package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherModel

class WeatherScreenViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(temperature = "")

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnButtonClicked -> {
                val temperature = getWeather().temp
                previousState.copy(temperature = temperature)
            }
            else -> null
        }
    }

    private suspend fun getWeather(): WeatherModel {
        return interactor.getWeather()
    }
}