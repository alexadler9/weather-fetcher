package ru.alexadler9.weatherfetcher.data

import ru.alexadler9.weatherfetcher.data.local.AppPreferencesSource

class PreferencesRepositoryImpl(private val appPreferencesSource: AppPreferencesSource) :
    PreferencesRepository {

    override fun getCity() = appPreferencesSource.getCity()

    override fun setCity(city: String) {
        appPreferencesSource.setCity(city)
    }
}