package com.example.weatherfetcher.feature.data

import com.example.weatherfetcher.feature.domain.WeatherModel

class WeatherRepoImpl(private val weatherRemoteSource: WeatherRemoteSource) : WeatherRepo {

    override suspend fun getWeather(): WeatherModel {
        return weatherRemoteSource.getWeather().toDomain()
    }
}