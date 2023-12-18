package ru.alexadler9.weatherfetcher.data.local

import co.windly.ktxprefs.annotation.DefaultString
import co.windly.ktxprefs.annotation.Prefs

@Prefs(value = "AppPreferences")
class AppPreferences(
    @DefaultString(value = "Москва")
    internal val city: String
)