package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherMainRemoteModel(
    @SerializedName("temp")
    val temp: String,
    @SerializedName("feels_like")
    val tempFeelsLike: String,
    @SerializedName("humidity")
    val humidity: String
)