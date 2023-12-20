package ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model

data class GeoModel(
    /** Geographical coordinates of the found location (latitude). */
    val latitude: Double,

    /** Geographical coordinates of the found location (longitude). */
    val longitude: Double,

    /** Country of the found location. */
    val country: String,

    /** Name of the found location in different languages. Pairs of the type "language code - name". */
    val localNames: Map<String, String>
)