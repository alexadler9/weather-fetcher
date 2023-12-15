package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherModel

fun WeatherRemoteModel.toDomain() = WeatherModel(
    temp = this.main.temp
)