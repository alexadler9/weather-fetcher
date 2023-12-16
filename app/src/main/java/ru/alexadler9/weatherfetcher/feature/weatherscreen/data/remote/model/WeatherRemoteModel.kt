package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherRemoteModel(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("main")
    val main: WeatherMainRemoteModel,
    @SerializedName("wind")
    val wind: WeatherWindRemoteModel,
    @SerializedName("weather")
    val details: List<WeatherDetailsRemoteModel>
)