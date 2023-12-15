package ru.alexadler9.weatherfetcher.feature.weatherscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenViewModel

val weatherModule = module {

    viewModel<WeatherScreenViewModel> { WeatherScreenViewModel(get<WeatherInteractor>()) }
}