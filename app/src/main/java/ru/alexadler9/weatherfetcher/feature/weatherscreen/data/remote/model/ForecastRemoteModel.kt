package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastRemoteModel(
    /** A number of timestamps returned in the API response. */
    @SerializedName("cnt")
    val cnt: Int,

    /** City data. */
    @SerializedName("city")
    val city: ForecastCityRemoteModel,

    /** List of forecast data ([cnt] records with 3-hour step). */
    @SerializedName("list")
    val weather: List<ForecastWeatherRemoteModel>
)
