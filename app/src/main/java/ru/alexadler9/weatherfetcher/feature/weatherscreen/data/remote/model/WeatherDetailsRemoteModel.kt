package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherDetailsRemoteModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)
