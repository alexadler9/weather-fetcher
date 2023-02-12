package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.Event

data class ViewState(
    val temperature: String
)

sealed class UiEvent() : Event {
    object OnButtonClicked : UiEvent()
}