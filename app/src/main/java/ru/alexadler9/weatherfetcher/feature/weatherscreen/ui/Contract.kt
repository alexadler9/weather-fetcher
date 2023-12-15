package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui

import ru.alexadler9.weatherfetcher.base.Event

data class ViewState(
    val temperature: String
)

sealed class UiEvent() : Event {
    object OnButtonClicked : UiEvent()
}