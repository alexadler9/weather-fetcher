package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model

import com.google.gson.annotations.SerializedName

data class GeoRemoteModel(
    /** Name of the found location. */
    @SerializedName("name")
    val name: String,

    /** Name of the found location in different languages. Pairs of the type "language code - name". */
    @SerializedName("local_names")
    val localNames: Map<String, String>,

    /** Geographical coordinates of the found location (latitude). */
    @SerializedName("lat")
    val lat: Double,

    /** Geographical coordinates of the found location (longitude). */
    @SerializedName("lon")
    val lon: Double,

    /** Country of the found location. */
    @SerializedName("country")
    val country: String
)
