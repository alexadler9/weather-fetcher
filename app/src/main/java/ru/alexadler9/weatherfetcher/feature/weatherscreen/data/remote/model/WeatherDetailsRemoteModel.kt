package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherDetailsRemoteModel(
    /** Weather condition id. */
    @SerializedName("id")
    val id: Int,

    /** Group of weather parameters (Rain, Snow, Clouds etc.). */
    @SerializedName("main")
    val main: String,

    /** Weather condition within the group. */
    @SerializedName("description")
    val description: String,

    /** Weather icon id. */
    @SerializedName("icon")
    val icon: String
)
