package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastRemoteModel(
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("city")
    val city: ForecastCityRemoteModel,
    @SerializedName("list")
    val weather: List<ForecastWeatherRemoteModel>
)
