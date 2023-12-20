package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastCityRemoteModel(
    /** Shift in seconds from UTC. */
    @SerializedName("timezone")
    val timezone: Int
)
