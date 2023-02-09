package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.WEATHER_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val weatherApi = retrofit.create(WeatherApi::class.java)

    fun getApi(): WeatherApi {
        return weatherApi
    }
}