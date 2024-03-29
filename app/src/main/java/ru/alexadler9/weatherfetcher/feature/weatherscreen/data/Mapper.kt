package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import ru.alexadler9.weatherfetcher.BuildConfig
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.GeoRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Mappers from remote weather models to local models.
 */

private const val DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm"

private fun convertTimestampToDateString(timestamp: Long, pattern: String = DATE_TIME_PATTERN) =
    SimpleDateFormat(pattern, Locale.getDefault())
        .apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.format(Date(timestamp * 1000L))

private fun convertTimestampToCalendar(timestamp: Long) =
    GregorianCalendar.getInstance().apply {
        timeZone = TimeZone.getTimeZone("UTC")
        time = Date(timestamp * 1000L)
    }

fun WeatherRemoteModel.toDomain() = WeatherModel(
    datetime = convertTimestampToDateString(this.dt + this.timezone),
    temp = this.main.temp,
    tempFeelsLike = this.main.tempFeelsLike,
    humidity = this.main.humidity,
    windSpeed = this.wind.speed,
    details = this.details.map {
        WeatherDetailsModel(
            description = it.description,
            icon = BuildConfig.WEATHER_ICON_URL.format(it.icon)
        )
    }
)

fun ForecastRemoteModel.toDomain(): ForecastModel {
    // Let's try to convert the forecast-every-three-hours into a forecast-daily for the next three days
    val dailyForecast: MutableList<ForecastWeatherModel> = mutableListOf()

    // Group hourly forecasts by day of the month
    val detailsForecast = this.weather.groupBy {
        convertTimestampToCalendar(it.dt + this.city.timezone)[Calendar.DAY_OF_MONTH]
    }

    for ((_, forecasts) in detailsForecast) {
        if (forecasts.size != 8) {
            // Forecast data is incomplete
            continue
        }

        val forecastNight = forecasts[0]
        val forecastDay = forecasts[4]
        val calendar = convertTimestampToCalendar(forecastDay.dt + this.city.timezone)

        dailyForecast.add(
            ForecastWeatherModel(
                date = "${calendar[Calendar.DAY_OF_MONTH]} ${
                    calendar.getDisplayName(
                        Calendar.MONTH,
                        Calendar.SHORT,
                        Locale("ru")
                    )
                }",
                dayOfWeek = calendar.getDisplayName(
                    Calendar.DAY_OF_WEEK,
                    Calendar.SHORT,
                    Locale("ru")
                )!!,
                tempDay = forecastDay.main.temp,
                tempNight = forecastNight.main.temp,
                humidity = forecastDay.main.humidity,
                windSpeed = forecastDay.wind.speed,
                details = forecastDay.details.map {
                    WeatherDetailsModel(
                        description = it.description,
                        icon = BuildConfig.WEATHER_ICON_URL.format(it.icon)
                    )
                }
            )
        )

        if (dailyForecast.size == 3) {
            break
        }
    }

    return ForecastModel(weather = dailyForecast)
}

fun GeoRemoteModel.toDomain() = GeoModel(
    latitude = this.lat,
    longitude = this.lon,
    country = this.country,
    localNames = this.localNames
)