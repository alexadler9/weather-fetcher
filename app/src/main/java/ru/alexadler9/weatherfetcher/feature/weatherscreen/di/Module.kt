package ru.alexadler9.weatherfetcher.feature.weatherscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment.ForecastFragmentViewModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment.WeatherFragmentViewModel

val weatherModule = module {

    single<WeatherInteractor> {
        WeatherInteractor(
            get<WeatherRepository>(),
            get<PreferencesRepository>()
        )
    }

    viewModel<WeatherFragmentViewModel> { WeatherFragmentViewModel(get<WeatherInteractor>()) }

    viewModel<ForecastFragmentViewModel> { ForecastFragmentViewModel(get<WeatherInteractor>()) }
}