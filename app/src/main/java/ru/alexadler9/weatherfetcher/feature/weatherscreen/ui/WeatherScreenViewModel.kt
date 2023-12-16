package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class WeatherScreenViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState {
        weatherLoad()
        return ViewState(state = State.Load)
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnButtonClicked -> {
                weatherLoad()
                previousState.copy(state = State.Load)
            }

            is DataEvent.OnWeatherFetchSucceed -> {
                previousState.copy(state = State.Content(event.weatherModel))
            }

            is DataEvent.OnWeatherFetchFailed -> {
                return previousState.copy(state = State.Error(event.error))
            }

            else -> null
        }
    }

    private fun weatherLoad() {
        viewModelScope.launch {
            interactor.getWeather().fold(
                onError = {
                    processDataEvent(DataEvent.OnWeatherFetchFailed(error = it))
                },
                onSuccess = {
                    processDataEvent(DataEvent.OnWeatherFetchSucceed(weatherModel = it))
                }
            )
        }
    }
}