package ru.alexadler9.weatherfetcher.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.data.PreferencesRepositoryImpl
import ru.alexadler9.weatherfetcher.data.local.AppPreferencesSource

val prefsModule = module {

    single<AppPreferencesSource> { AppPreferencesSource(androidApplication()) }

    single<PreferencesRepository> { PreferencesRepositoryImpl(get<AppPreferencesSource>()) }
}