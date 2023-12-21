package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class ForecastFragmentViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState {
        forecastLoad(interactor.getCity())
        return ViewState(
            cityEditable = "",
            city = interactor.getCity(),
            state = State.Load
        )
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnUpdateButtonClicked -> {
                forecastLoad(previousState.city)
                previousState.copy(state = State.Load)
            }

            is UiEvent.OnCitySearchEdit -> {
                previousState.copy(cityEditable = event.text)
            }

            is UiEvent.OnCitySearchButtonClicked -> {
                interactor.setCity(previousState.cityEditable)
                forecastLoad(previousState.cityEditable)
                previousState.copy(
                    cityEditable = "",
                    city = previousState.cityEditable,
                    state = State.Load
                )
            }

            is DataEvent.OnForecastLoadSucceed -> {
                previousState.copy(state = State.Content(event.forecastModel))
            }

            is DataEvent.OnForecastLoadFailed -> {
                previousState.copy(state = State.Error(event.error))
            }

            is DataEvent.OnForecastNotFound -> {
                previousState.copy(state = State.NotFound)
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
                    if (geoModel.isEmpty() ||
                        !geoModel.first().localNames.containsKey("ru") ||
                        !geoModel.first().localNames["ru"].equals(city, true)
                    ) {
                        processDataEvent(DataEvent.OnForecastNotFound)
                    } else {
                        val geo = geoModel.first()
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