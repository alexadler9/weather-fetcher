package ru.alexadler9.weatherfetcher.data.local

import android.app.Application

/**
 * Source for accessing user settings.
 */
class AppPreferencesSource(private val application: Application) {

    /**
     * Get the last city specified by user.
     */
    fun getCity() = application.requireAppPreferences().getCity()

    /**
     * Save the city.
     * @param city City name.
     */
    fun setCity(city: String) {
        application.requireAppPreferences().setCity(city)
    }
}