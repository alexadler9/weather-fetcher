package com.example.weatherfetcher.feature.data

import com.example.weatherfetcher.feature.data.model.WeatherRemoteModel
import com.example.weatherfetcher.feature.domain.WeatherModel

fun WeatherRemoteModel.toDomain() = WeatherModel(
    temperature = this.main.temperature,
    windDeg = this.wind.deg
)