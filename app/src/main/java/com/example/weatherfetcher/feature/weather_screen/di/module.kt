package com.example.weatherfetcher.feature.weather_screen.di

import com.example.weatherfetcher.WEATHER_BASE_URL
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.WeatherApi
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRemoteSource
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenPresenter
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val weatherScreenModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }

    single<WeatherRemoteSource> { WeatherRemoteSource(get<WeatherApi>()) }

    single<WeatherRepo> { WeatherRepoImpl(get<WeatherRemoteSource>()) }

    single<WeatherInteractor> { WeatherInteractor(get<WeatherRepo>()) }

    single<WeatherScreenPresenter> { WeatherScreenPresenter(get<WeatherInteractor>()) }
}