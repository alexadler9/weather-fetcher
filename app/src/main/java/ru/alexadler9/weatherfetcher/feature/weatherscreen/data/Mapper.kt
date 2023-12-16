package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherDetailsModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherModel
import java.text.SimpleDateFormat
import java.util.*

private fun getDateTime(timestamp: Long) = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    .apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.format(Date(timestamp * 1000L))

fun WeatherRemoteModel.toDomain() = WeatherModel(
    datetime = getDateTime(this.dt + this.timezone),
    temp = this.main.temp,
    tempFeelsLike = this.main.tempFeelsLike,
    humidity = this.main.humidity,
    windSpeed = this.wind.speed,
    details = this.details.map {
        WeatherDetailsModel(
            id = it.id,
            main = it.main,
            description = it.description,
            icon = it.icon
        )
    }
)