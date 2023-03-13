package com.example.weatherfetcher.feature.weather_screen.ui

import androidx.lifecycle.viewModelScope
import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import kotlinx.coroutines.launch

class WeatherScreenViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        isLoading = false,
        temperature = "",
        windDeg = ""
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnButtonClicked -> {
                viewModelScope.launch {
                    interactor.getWeather().fold(
                        onError = {
                            processDataEvent(DataEvent.OnWeatherFetchFailed(error = it))
                        },
                        onSuccess = {
                            processDataEvent(
                                DataEvent.OnWeatherFetchSucceed(
                                    temperature = it.temperature,
                                    windDeg = it.windDeg
                                )
                            )
                        }
                    )
                }
                return previousState.copy(isLoading = true, temperature = "", windDeg = "")
            }

            is DataEvent.OnWeatherFetchSucceed -> {
                return previousState.copy(
                    isLoading = false,
                    temperature = event.temperature,
                    windDeg = event.windDeg
                )
            }

            else -> return null
        }
    }
}