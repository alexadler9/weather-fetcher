package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastWeatherRemoteModel(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("main")
    val main: WeatherMainRemoteModel,
    @SerializedName("wind")
    val wind: WeatherWindRemoteModel,
    @SerializedName("weather")
    val details: List<WeatherDetailsRemoteModel>
)
