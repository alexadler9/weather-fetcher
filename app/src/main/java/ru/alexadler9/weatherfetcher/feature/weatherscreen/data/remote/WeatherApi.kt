package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexadler9.weatherfetcher.di.WEATHER_API_KEY
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): WeatherRemoteModel
}