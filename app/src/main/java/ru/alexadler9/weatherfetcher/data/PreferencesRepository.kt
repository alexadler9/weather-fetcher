package ru.alexadler9.weatherfetcher.data

interface PreferencesRepository {
    fun getCity(): String
    fun setCity(city: String)
}