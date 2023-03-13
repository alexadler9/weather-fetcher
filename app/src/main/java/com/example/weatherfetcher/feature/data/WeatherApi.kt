package com.example.weatherfetcher.feature.data

import com.example.weatherfetcher.feature.data.model.WeatherRemoteModel
import com.example.weatherfetcher.feature.di.WEATHER_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): WeatherRemoteModel
}