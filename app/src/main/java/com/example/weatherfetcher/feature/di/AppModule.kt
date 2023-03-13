package com.example.weatherfetcher.feature.di

import com.example.weatherfetcher.feature.data.WeatherApi
import com.example.weatherfetcher.feature.data.WeatherRemoteSource
import com.example.weatherfetcher.feature.data.WeatherRepo
import com.example.weatherfetcher.feature.data.WeatherRepoImpl
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val WEATHER_API_KEY = "63defae2a41ce41a3f41adfd722c0e72"
const val WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"

val networkModule = module {

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

    viewModel<WeatherScreenViewModel> { WeatherScreenViewModel(get<WeatherInteractor>()) }
}