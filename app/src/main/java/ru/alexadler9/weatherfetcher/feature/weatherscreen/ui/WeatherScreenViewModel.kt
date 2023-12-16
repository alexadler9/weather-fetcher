package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.WeatherGeoModel

class WeatherScreenViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState {
        coordinatesLoad("Челябинск")
        return ViewState(state = State.Load)
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnButtonClicked -> {
                coordinatesLoad("Челябинск")
                previousState.copy(state = State.Load)
            }

            is DataEvent.OnCoordinatesLoadSucceed -> {
                Log.d(this.javaClass.simpleName, event.coordinates.toString())
                if (event.coordinates.isEmpty()) {
                    previousState.copy(state = State.NotFound)
                } else {
                    weatherLoad(event.coordinates[0])
                    null
                }
            }

            is DataEvent.OnWeatherLoadSucceed -> {
                previousState.copy(state = State.Content(event.weatherModel))
            }

            is DataEvent.OnDataLoadFailed -> {
                return previousState.copy(state = State.Error(event.error))
            }

            else -> null
        }
    }

    private fun coordinatesLoad(city: String) {
        viewModelScope.launch {
            interactor.getCoordinates(city).fold(
                onError = {
                    processDataEvent(DataEvent.OnDataLoadFailed(error = it))
                },
                onSuccess = {
                    processDataEvent(DataEvent.OnCoordinatesLoadSucceed(coordinates = it))
                }
            )
        }
    }

    private fun weatherLoad(coordinates: WeatherGeoModel) {
        viewModelScope.launch {
            interactor.getWeather(
                coordinates.latitude.toString(),
                coordinates.longitude.toString()
            ).fold(
                onError = {
                    processDataEvent(DataEvent.OnDataLoadFailed(error = it))
                },
                onSuccess = {
                    processDataEvent(DataEvent.OnWeatherLoadSucceed(weatherModel = it))
                }
            )
        }
    }
}