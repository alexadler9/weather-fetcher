package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexadler9.weatherfetcher.di.WEATHER_API_KEY
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.GeoRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): WeatherRemoteModel

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): ForecastRemoteModel

    @GET("geo/1.0/direct")
    suspend fun getCoordinates(
        @Query("q") city: String,
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): List<GeoRemoteModel>
}