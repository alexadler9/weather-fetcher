package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexadler9.weatherfetcher.base.Action
import ru.alexadler9.weatherfetcher.base.BaseViewModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor

class ForecastViewModel(private val interactor: WeatherInteractor) :
    BaseViewModel<ViewState, ViewEvent>() {

    override val initialViewState = ViewState(
        cityEditable = "",
        city = interactor.getCity(),
        state = State.Load
    )

    init {
        forecastLoad(interactor.getCity())
    }

    override fun reduce(action: Action, previousState: ViewState): ViewState? {
        return when (action) {
            is UiAction.OnUpdateButtonClicked -> {
                forecastLoad(previousState.city)
                previousState.copy(state = State.Load)
            }

            is UiAction.OnCitySearchEdited -> {
                previousState.copy(cityEditable = action.text)
            }

            is UiAction.OnCitySearchButtonClicked -> {
                interactor.setCity(previousState.cityEditable)
                forecastLoad(previousState.cityEditable)
                previousState.copy(
                    cityEditable = "",
                    city = previousState.cityEditable,
                    state = State.Load
                )
            }

            is DataAction.OnForecastLoadSucceed -> {
                previousState.copy(state = State.Content(action.forecastModel))
            }

            is DataAction.OnForecastLoadFailed -> {
                previousState.copy(state = State.Error(action.error))
            }

            is DataAction.OnForecastNotFound -> {
                previousState.copy(state = State.NotFound)
            }

            else -> null
        }
    }

    private fun forecastLoad(city: String) = viewModelScope.launch {
        interactor.getCoordinates(city).fold(
            onError = {
                processDataAction(DataAction.OnForecastLoadFailed(error = it))
            },
            onSuccess = { geoModel ->
                if (geoModel.isEmpty() ||
                    !geoModel.first().localNames.containsKey("ru") ||
                    !geoModel.first().localNames["ru"].equals(city, true)
                ) {
                    processDataAction(DataAction.OnForecastNotFound)
                } else {
                    val geo = geoModel.first()
                    interactor.getForecast(
                        geo.latitude.toString(),
                        geo.longitude.toString()
                    ).fold(
                        onError = {
                            processDataAction(DataAction.OnForecastLoadFailed(error = it))
                        },
                        onSuccess = {
                            processDataAction(DataAction.OnForecastLoadSucceed(forecastModel = it))
                        }
                    )
                }
            }
        )
    }
}