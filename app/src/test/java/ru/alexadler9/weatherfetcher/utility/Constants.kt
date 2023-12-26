package ru.alexadler9.weatherfetcher.utility

import ru.alexadler9.weatherfetcher.BuildConfig
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.*
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.*

const val CITY_NAME_INCORRECT = "123456"
const val CITY_NAME_1 = "Москва"
const val CITY_NAME_2 = "Челябинск"

val GEO_MODEL_1 = GeoModel(
    latitude = 55.761665,
    longitude = 37.606667,
    country = "RU",
    localNames = mapOf("ru" to CITY_NAME_1)
)

val GEO_MODEL_2 = GeoModel(
    latitude = 61.429722,
    longitude = 55.154442,
    country = "RU",
    localNames = mapOf("ru" to CITY_NAME_2)
)

val WEATHER_MODEL = WeatherModel(
    datetime = "01.01.2024 12:00",
    temp = "0.00",
    tempFeelsLike = "-2.00",
    humidity = "23",
    windSpeed = "2",
    details = listOf(
        WeatherDetailsModel(
            description = "ясно",
            icon = BuildConfig.WEATHER_ICON_URL.format("01")
        )
    )
)

val FORECAST_MODEL = ForecastModel(
    listOf(
        ForecastWeatherModel(
            date = "1 янв.",
            dayOfWeek = "ПН",
            tempDay = "0.00",
            tempNight = "0.00",
            humidity = "23",
            windSpeed = "2",
            details = listOf(
                WeatherDetailsModel(
                    description = "ясно",
                    icon = BuildConfig.WEATHER_ICON_URL.format("01")
                )
            )
        )
    )
)

val WEATHER_REMOTE_MODEL = WeatherRemoteModel(
    dt = 1704110400,
    timezone = 0,
    main = WeatherMainRemoteModel(
        temp = "20.00",
        tempFeelsLike = "18.75",
        humidity = "23"
    ),
    wind = WeatherWindRemoteModel(
        speed = "2"
    ),
    details = listOf(
        WeatherDetailsRemoteModel(
            id = 0,
            main = "Ясно",
            description = "Ясно",
            icon = "01"
        )
    )
)

val FORECAST_WEATHER_REMOTE_MODEL = ForecastWeatherRemoteModel(
    dt = 1704110400,
    main = WeatherMainRemoteModel(
        temp = "20.00",
        tempFeelsLike = "18.75",
        humidity = "23"
    ),
    wind = WeatherWindRemoteModel(
        speed = "2"
    ),
    details = listOf(
        WeatherDetailsRemoteModel(
            id = 0,
            main = "Ясно",
            description = "Ясно",
            icon = "01"
        )
    )
)

val GEO_REMOTE_MODEL = GeoRemoteModel(
    name = "Москва",
    localNames = mapOf("ru" to "Москва"),
    lat = 55.761665,
    lon = 37.606667,
    country = "RU"
)