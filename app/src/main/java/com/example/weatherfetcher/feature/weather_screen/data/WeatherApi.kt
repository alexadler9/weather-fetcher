package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.WEATHER_API_KEY
import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): WeatherRemoteModel
}