package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

/**
 * Contract coordinating UI control between MainActivity and Fragments.
 */
interface WeatherScreenContract {
    interface ActivityCallbacks {
        fun onCitySearchUpdated(text: String)
    }

    interface FragmentCallbacks {
        fun onCitySearchEdited(text: String)
        fun onCitySearchButtonClicked()
    }
}