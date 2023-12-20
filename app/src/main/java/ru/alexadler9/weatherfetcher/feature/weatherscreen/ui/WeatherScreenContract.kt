package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

/**
 * Contract coordinating UI control between MainActivity and Fragments.
 */
interface WeatherScreenContract {
    interface Activity {
        fun citySearchEditSetText(text: String)
        fun citySearchButtonSetEnabled(enabled: Boolean)
    }

    interface Fragment {
        fun onCitySearchEditChanged(text: String)
        fun onCitySearchButtonClicked()
    }
}