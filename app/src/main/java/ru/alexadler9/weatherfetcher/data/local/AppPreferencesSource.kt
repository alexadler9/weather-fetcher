package ru.alexadler9.weatherfetcher.data.local

import android.app.Application

class AppPreferencesSource(private val application: Application) {

    fun getCity() = application.requireAppPreferences().getCity()

    fun setCity(city: String) {
        application.requireAppPreferences().setCity(city)
    }
}