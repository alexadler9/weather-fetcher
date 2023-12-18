package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastCityRemoteModel(
    @SerializedName("timezone")
    val timezone: Int
)
