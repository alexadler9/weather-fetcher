package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherMainRemoteModel(
    /** Temperature. */
    @SerializedName("temp")
    val temp: String,

    /** Temperature. This temperature parameter accounts for the human perception of weather. */
    @SerializedName("feels_like")
    val tempFeelsLike: String,

    /** Humidity, %. */
    @SerializedName("humidity")
    val humidity: String
)