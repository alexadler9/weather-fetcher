package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.mock

import android.content.Context
import okhttp3.Request
import okhttp3.Response
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import java.io.IOException
import java.io.InputStream

class RequestsHandler(
    private val context: Context,
    private val preferencesRepository: PreferencesRepository
) {

    private val responsesMap = mutableMapOf(
        "data/2.5/weather" to "weather.json",
        "data/2.5/forecast" to "forecast.json",
        "geo/1.0/direct" to "geo.json"
    )

    fun shouldIntercept(path: String): Boolean {
        for (interceptUrl in responsesMap.keys) {
            if (path.contains(interceptUrl)) {
                return true
            }
        }
        return false
    }

    fun proceed(request: Request, path: String): Response {
        when (preferencesRepository.getCity().lowercase()) {
            "error" -> {
                return errorResponse(request, 400, "Error for path " + path)
            }
            else -> {
                responsesMap.keys.forEach {
                    if (path.contains(it)) {
                        val mockResponsePath: String = responsesMap[it]!!
                        return createResponseFromAssets(request, mockResponsePath)
                    }
                }
            }
        }
        return errorResponse(request, 500, "Incorrectly intercepted request")
    }

    private fun createResponseFromAssets(
        request: Request,
        assetPath: String
    ): Response {
        return try {
            val stream: InputStream = context.assets.open(assetPath)
            stream.use {
                successResponse(request, stream)
            }
        } catch (e: IOException) {
            errorResponse(request, 500, e.message ?: "IOException")
        }
    }
}