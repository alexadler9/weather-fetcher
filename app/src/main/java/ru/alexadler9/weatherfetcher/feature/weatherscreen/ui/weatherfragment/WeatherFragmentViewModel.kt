package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.base.Event
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class WeatherFragmentViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState {
        weatherLoad(interactor.getCity())
        return ViewState(
            cityEditable = "",
            city = interactor.getCity(),
            state = State.Load
        )
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is UiEvent.OnUpdateButtonClicked -> {
                weatherLoad(previousState.city)
                previousState.copy(state = State.Load)
            }

            is UiEvent.OnCitySearchEdited -> {
                previousState.copy(cityEditable = event.text)
            }

            is UiEvent.OnCitySearchButtonClicked -> {
                interactor.setCity(previousState.cityEditable)
                weatherLoad(previousState.cityEditable)
                previousState.copy(
                    cityEditable = "",
                    city = previousState.cityEditable,
                    state = State.Load
                )
            }

            is DataEvent.OnWeatherLoadSucceed -> {
                previousState.copy(state = State.Content(event.weatherModel))
            }

            is DataEvent.OnWeatherLoadFailed -> {
                previousState.copy(state = State.Error(event.error))
            }

            is DataEvent.OnWeatherNotFound -> {
                previousState.copy(state = State.NotFound)
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
                    if (geoModel.isEmpty() ||
                        !geoModel.first().localNames.containsKey("ru") ||
                        !geoModel.first().localNames["ru"].equals(city, true)
                    ) {
                        processDataEvent(DataEvent.OnWeatherNotFound)
                    } else {
                        val geo = geoModel.first()
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