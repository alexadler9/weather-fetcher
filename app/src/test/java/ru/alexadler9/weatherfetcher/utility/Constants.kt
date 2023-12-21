package ru.alexadler9.weatherfetcher.utility

import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.*

val CITY_NAME_INCORRECT = "123456"
val CITY_NAME_1 = "Москва"
val CITY_NAME_2 = "Челябинск"

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
            id = 0,
            main = "ясно",
            description = "ясно",
            icon = ""
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
                    id = 0,
                    main = "ясно",
                    description = "ясно",
                    icon = ""
                )
            )
        )
    )
)