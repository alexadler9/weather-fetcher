package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class WeatherFragmentViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState {
        weatherLoad("Челябинск")
        return ViewState(state = State.Load)
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnButtonClicked -> {
                weatherLoad("Челябинск")
                previousState.copy(state = State.Load)
            }

            is DataEvent.OnWeatherLoadSucceed -> {
                previousState.copy(state = State.Content(event.weatherModel))
            }

            is DataEvent.OnWeatherLoadFailed -> {
                return previousState.copy(state = State.Error(event.error))
            }

            is DataEvent.OnWeatherNotFound -> {
                return previousState.copy(state = State.NotFound)
            }

            else -> null
        }
    }

    private fun weatherLoad(city: String) {
        viewModelScope.launch {
            interactor.getCoordinates(city).fold(
                onError = {
                    processDataEvent(DataEvent.OnWeatherLoadFailed(error = it))
                },
                onSuccess = { geoModel ->
                    if (geoModel.isEmpty()) {
                        processDataEvent(DataEvent.OnWeatherNotFound)
                    } else {
                        val geo = geoModel.first()
                        Log.d(this.javaClass.simpleName, geo.toString())
                        interactor.getWeather(
                            geo.latitude.toString(),
                            geo.longitude.toString()
                        ).fold(
                            onError = {
                                processDataEvent(DataEvent.OnWeatherLoadFailed(error = it))
                            },
                            onSuccess = {
                                processDataEvent(DataEvent.OnWeatherLoadSucceed(weatherModel = it))
                            }
                        )
                    }
                }
            )
        }
    }
}