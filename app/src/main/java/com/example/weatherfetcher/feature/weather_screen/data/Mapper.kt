package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemoteModel
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

fun WeatherRemoteModel.toDomain() = WeatherModel(
    temperature = this.main.temperature
)