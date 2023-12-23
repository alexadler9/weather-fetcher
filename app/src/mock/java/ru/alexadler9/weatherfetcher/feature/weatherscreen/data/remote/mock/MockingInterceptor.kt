package ru.alexadler9.weatherfetcher.feature.weatherscreen.data.remote.mock

import android.os.SystemClock
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Intercepts and processes requests.
 */
class MockingInterceptor(private val requestHandler: RequestsHandler) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        if (requestHandler.shouldIntercept(path)) {
            // Get a mock response
            val response = requestHandler.proceed(request, path)
            SystemClock.sleep(200)
            return response
        }
        return chain.proceed(request)
    }
}