package ru.alexadler9.weatherfetcher.feature.weatherscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment.ForecastViewModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment.WeatherViewModel

val weatherModule = module {

    single<WeatherInteractor> {
        WeatherInteractor(
            get<WeatherRepository>(),
            get<PreferencesRepository>()
        )
    }

    viewModel<WeatherViewModel> { WeatherViewModel(get<WeatherInteractor>()) }

    viewModel<ForecastViewModel> { ForecastViewModel(get<WeatherInteractor>()) }
}