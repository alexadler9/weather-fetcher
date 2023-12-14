package ru.alexadler9.weatherfetcher.feature.weatherscreen.di

import org.koin.dsl.module
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenPresenter

val weatherModule = module {

    single<WeatherScreenPresenter> { WeatherScreenPresenter(get<WeatherInteractor>()) }
}