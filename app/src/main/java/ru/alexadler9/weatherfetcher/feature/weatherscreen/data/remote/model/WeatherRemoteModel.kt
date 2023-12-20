package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherRemoteModel(
    /** Time of data calculation, unix, UTC. */
    @SerializedName("dt")
    val dt: Long,

    /** Shift in seconds from UTC. */
    @SerializedName("timezone")
    val timezone: Int,

    /** Main weather data. */
    @SerializedName("main")
    val main: WeatherMainRemoteModel,

    /** Wind data. */
    @SerializedName("wind")
    val wind: WeatherWindRemoteModel,

    /** List of weather conditions data. */
    @SerializedName("weather")
    val details: List<WeatherDetailsRemoteModel>
)