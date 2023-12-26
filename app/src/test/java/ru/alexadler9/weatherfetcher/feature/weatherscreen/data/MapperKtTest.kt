package ru.alexadler9.weatherfetcher.feature.weatherscreen.data

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.alexadler9.weatherfetcher.BuildConfig
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastCityRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastWeatherRemoteModel
import ru.alexadler9.weatherfetcher.utility.FORECAST_WEATHER_REMOTE_MODEL
import ru.alexadler9.weatherfetcher.utility.GEO_REMOTE_MODEL
import ru.alexadler9.weatherfetcher.utility.WEATHER_REMOTE_MODEL

class MapperKtTest {

    @Test
    fun `converts remote weather model into a local`() {
        val weatherModel = WEATHER_REMOTE_MODEL.toDomain()

        assertEquals(weatherModel.datetime, "01.01.2024 12:00")
        assertEquals(weatherModel.temp, WEATHER_REMOTE_MODEL.main.temp)
        assertEquals(weatherModel.tempFeelsLike, WEATHER_REMOTE_MODEL.main.tempFeelsLike)
        assertEquals(weatherModel.humidity, WEATHER_REMOTE_MODEL.main.humidity)
        assertEquals(weatherModel.windSpeed, WEATHER_REMOTE_MODEL.wind.speed)
        assertThat(weatherModel.details.size, greaterThanOrEqualTo(1))
        assertEquals(weatherModel.details.size, WEATHER_REMOTE_MODEL.details.size)
        assertEquals(
            weatherModel.details.first().description,
            WEATHER_REMOTE_MODEL.details.first().description
        )
        assertEquals(
            weatherModel.details.first().icon,
            BuildConfig.WEATHER_ICON_URL.format(WEATHER_REMOTE_MODEL.details.first().icon)
        )
    }

    @Test
    fun `converts forecast-every-three-hours into a forecast-daily for the next three days`() {
        val listForecastWeatherRemoteModel = mutableListOf<ForecastWeatherRemoteModel>()
        for (i in 0..40) {
            // Change dt parameter with 3-hour step
            // As a result, we get a 5-day forecast including weather data with 3-hour step
            listForecastWeatherRemoteModel.add(
                FORECAST_WEATHER_REMOTE_MODEL.copy(dt = FORECAST_WEATHER_REMOTE_MODEL.dt + i * 10800)
            )
        }

        val forecastRemoteModel = ForecastRemoteModel(
            cnt = 40,
            city = ForecastCityRemoteModel(timezone = 0),
            weather = listForecastWeatherRemoteModel
        )

        val forecastModel = forecastRemoteModel.toDomain()

        assertEquals(forecastModel.weather.size, 3)
        assertEquals(forecastModel.weather[0].date, "2 янв.")
        assertEquals(forecastModel.weather[1].date, "3 янв.")
        assertEquals(forecastModel.weather[2].date, "4 янв.")
    }

    @Test
    fun `converts remote geo model into a local`() {
        val geoModel = GEO_REMOTE_MODEL.toDomain()

        assertEquals(geoModel.latitude, GEO_REMOTE_MODEL.lat)
        assertEquals(geoModel.longitude, GEO_REMOTE_MODEL.lon)
        assertEquals(geoModel.country, GEO_REMOTE_MODEL.country)
        assertEquals(geoModel.localNames, GEO_REMOTE_MODEL.localNames)
    }
}