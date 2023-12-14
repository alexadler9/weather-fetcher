package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val WEATHER_API_KEY = "63defae2a41ce41a3f41adfd722c0e72"
const val WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"

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