package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.Action
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class WeatherViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState, ViewEvent>() {

    override val initialViewState = ViewState(
        cityEditable = "",
        city = interactor.getCity(),
        state = State.Load
    )

    init {
        weatherLoad(interactor.getCity())
    }

    override fun reduce(action: Action, previousState: ViewState): ViewState? {
        return when (action) {
            is UiAction.OnUpdateButtonClicked -> {
                weatherLoad(previousState.city)
                previousState.copy(state = State.Load)
            }

            is UiAction.OnCitySearchEdited -> {
                previousState.copy(cityEditable = action.text)
            }

            is UiAction.OnCitySearchButtonClicked -> {
                interactor.setCity(previousState.cityEditable)
                weatherLoad(previousState.cityEditable)
                previousState.copy(
                    cityEditable = "",
                    city = previousState.cityEditable,
                    state = State.Load
                )
            }

            is DataAction.OnWeatherLoadSucceed -> {
                previousState.copy(state = State.Content(action.weatherModel))
            }

            is DataAction.OnWeatherLoadFailed -> {
                previousState.copy(state = State.Error(action.error))
            }

            is DataAction.OnWeatherNotFound -> {
                previousState.copy(state = State.NotFound)
            }

            else -> null
        }
    }

    private fun weatherLoad(city: String) = viewModelScope.launch {
        interactor.getCoordinates(city).fold(
            onError = {
                processDataAction(DataAction.OnWeatherLoadFailed(error = it))
            },
            onSuccess = { geoModel ->
                if (geoModel.isEmpty() ||
                    !geoModel.first().localNames.containsKey("ru") ||
                    !geoModel.first().localNames["ru"].equals(city, true)
                ) {
                    processDataAction(DataAction.OnWeatherNotFound)
                } else {
                    val geo = geoModel.first()
                    interactor.getWeather(
                        geo.latitude.toString(),
                        geo.longitude.toString()
                    ).fold(
                        onError = {
                            processDataAction(DataAction.OnWeatherLoadFailed(error = it))
                        },
                        onSuccess = {
                            processDataAction(DataAction.OnWeatherLoadSucceed(weatherModel = it))
                        }
                    )
                }
            }
        )
    }
}