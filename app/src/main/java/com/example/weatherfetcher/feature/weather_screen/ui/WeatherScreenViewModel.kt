package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

class WeatherScreenViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    suspend fun getWeather(): WeatherModel {
        return interactor.getWeather()
    }

    override fun initialViewState(): ViewState = ViewState(temperature = "")

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnButtonClicked -> {
                val temperature = getWeather().temperature
                return previousState.copy(temperature = temperature)
            }
            else -> return null
        }
    }
}