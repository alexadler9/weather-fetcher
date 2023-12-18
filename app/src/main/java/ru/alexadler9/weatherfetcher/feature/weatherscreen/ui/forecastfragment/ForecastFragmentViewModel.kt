package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class ForecastFragmentViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState {
        forecastLoad("Челябинск")
        return ViewState(state = State.Load)
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnButtonClicked -> {
                forecastLoad("Челябинск")
                previousState.copy(state = State.Load)
            }

            is DataEvent.OnForecastLoadSucceed -> {
                previousState.copy(state = State.Content(event.forecastModel))
            }

            is DataEvent.OnForecastLoadFailed -> {
                return previousState.copy(state = State.Error(event.error))
            }

            is DataEvent.OnForecastNotFound -> {
                return previousState.copy(state = State.NotFound)
            }

            else -> null
        }
    }

    private fun forecastLoad(city: String) {
        viewModelScope.launch {
            interactor.getCoordinates(city).fold(
                onError = {
                    processDataEvent(DataEvent.OnForecastLoadFailed(error = it))
                },
                onSuccess = { geoModel ->
                    if (geoModel.isEmpty()) {
                        processDataEvent(DataEvent.OnForecastNotFound)
                    } else {
                        val geo = geoModel.first()
                        Log.d(this.javaClass.simpleName, geo.toString())
                        interactor.getForecast(
                            geo.latitude.toString(),
                            geo.longitude.toString()
                        ).fold(
                            onError = {
                                processDataEvent(DataEvent.OnForecastLoadFailed(error = it))
                            },
                            onSuccess = {
                                processDataEvent(DataEvent.OnForecastLoadSucceed(forecastModel = it))
                            }
                        )
                    }
                }
            )
        }
    }
}