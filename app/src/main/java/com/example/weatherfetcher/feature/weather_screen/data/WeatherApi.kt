package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.WEATHER_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    fun getWeather(
        @Query("q") query: String,
        @Query("appid") apiKey: String = WEATHER_API_KEY
    ): Response<String>
}