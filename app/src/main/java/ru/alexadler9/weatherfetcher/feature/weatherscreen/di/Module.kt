package ru.alexadler9.weatherfetcher.feature.weatherscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment.ForecastFragmentViewModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment.WeatherFragmentViewModel

val weatherModule = module {

//    viewModel<WeatherFragmentViewModel> { (city: String) -> WeatherFragmentViewModel(get<WeatherInteractor>(), city) }
//
//    viewModel<ForecastFragmentViewModel> { (city: String) -> ForecastFragmentViewModel(get<WeatherInteractor>(), city) }

    viewModel<WeatherFragmentViewModel> { WeatherFragmentViewModel(get<WeatherInteractor>()) }

    viewModel<ForecastFragmentViewModel> { ForecastFragmentViewModel(get<WeatherInteractor>()) }
}