package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class WeatherScreenViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        isLoading = false,
        temperature = ""
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnButtonClicked -> {
                viewModelScope.launch {
                    interactor.getWeather().fold(
                        onError = {
                            processDataEvent(DataEvent.OnWeatherFetchFailed(error = it))
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnWeatherFetchSucceed(temperature = it.temp))
                        }
                    )
                }
                previousState.copy(isLoading = true)
            }

            is DataEvent.OnWeatherFetchSucceed -> {
                previousState.copy(isLoading = false, temperature = event.temperature)
            }

            else -> null
        }
    }
}