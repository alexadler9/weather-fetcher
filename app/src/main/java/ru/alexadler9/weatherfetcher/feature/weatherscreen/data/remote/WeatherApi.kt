package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexadler9.weatherfetcher.BuildConfig
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.ForecastRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.GeoRemoteModel
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.model.WeatherRemoteModel

/**
 * API for accessing weather data (based on OpenWeather API).
 */
interface WeatherApi {
    /**
     * Get current weather data for any location.
     * @param lat Latitude.
     * @param lon Longitude.
     * @param units Units of measurement. standard, metric and imperial units are available.
     * @param lang Output language.
     * @param apiKey Unique API key.
     */
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY
    ): WeatherRemoteModel

    /**
     * Get a 5-day forecast including weather data with 3-hour step.
     * @param lat Latitude.
     * @param lon Longitude.
     * @param units Units of measurement. standard, metric and imperial units are available.
     * @param lang Output language.
     * @param apiKey Unique API key.
     */
    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru",
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY
    ): ForecastRemoteModel

    /**
     * Get coordinates for a given geographical name.
     * @param city City name, state code (only for the US) and country code divided by comma.
     * @param apiKey Unique API key.
     */
    @GET("geo/1.0/direct")
    suspend fun getCoordinates(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY
    ): List<GeoRemoteModel>
}