package ru.alexadler9.weatherfetcher.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepositoryImpl
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherApi
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.WeatherRemoteSource

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<WeatherApi> { get<Retrofit>().create(WeatherApi::class.java) }

    single<WeatherRemoteSource> { WeatherRemoteSource(get<WeatherApi>()) }

    single<WeatherRepository> { WeatherRepositoryImpl(get<WeatherRemoteSource>()) }
}