package ru.alexadler9.weatherfetcher.data

/**
 * Repository for working with local app settings.
 */
interface PreferencesRepository {
    /**
     * Get the last city specified by user.
     */
    fun getCity(): String

    /**
     * Save the city.
     * @param city City name.
     */
    fun setCity(city: String)
}