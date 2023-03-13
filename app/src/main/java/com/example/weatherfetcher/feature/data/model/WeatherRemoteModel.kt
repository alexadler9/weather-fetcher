package com.example.weatherfetcher.feature.data.model

import com.google.gson.annotations.SerializedName

data class WeatherRemoteModel(
    @SerializedName("main")
    val main: WeatherMainRemoteModel,
    @SerializedName("wind")
    val wind: WeatherWindRemoteModel
)
