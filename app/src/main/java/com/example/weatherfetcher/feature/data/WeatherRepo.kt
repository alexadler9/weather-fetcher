package com.example.weatherfetcher.feature.data

import com.example.weatherfetcher.feature.domain.WeatherModel

interface WeatherRepo {

    suspend fun getWeather(): WeatherModel
}