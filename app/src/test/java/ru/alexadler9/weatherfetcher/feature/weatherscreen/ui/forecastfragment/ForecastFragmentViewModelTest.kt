package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.utility.*
import ru.alexadler9.weatherfetcher.utility.ext.CoroutinesTestExtension
import ru.alexadler9.weatherfetcher.utility.ext.InstantExecutorExtension

@ExtendWith(InstantExecutorExtension::class, CoroutinesTestExtension::class)
class ForecastFragmentViewModelTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var preferencesRepository: PreferencesRepository
    private lateinit var weatherInteractor: WeatherInteractor
    private lateinit var subject: ForecastFragmentViewModel

    @BeforeEach
    fun setUp() {
        weatherRepository = mock(WeatherRepository::class.java)
        preferencesRepository = mock(PreferencesRepository::class.java)
        weatherInteractor = WeatherInteractor(weatherRepository, preferencesRepository)
        subject = ForecastFragmentViewModel(weatherInteractor)

        `when`(preferencesRepository.setCity(anyString())).then { }
    }

    /* runTest is a coroutine builder designed for testing. Use this to wrap any tests that include coroutines */
    @Test
    fun `successful loading of forecast data`() = runTest {
        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(listOf(GEO_MODEL_1))
        `when`(weatherRepository.getForecast(anyString(), anyString())).thenReturn(FORECAST_MODEL)

        subject.processUiEvent(UiEvent.OnUpdateButtonClicked)

        // Loads 2 times, once during lazy initialization, the second time after “pressing a button”
        verify(weatherRepository, times(2)).getCoordinates(CITY_NAME_1)
        verify(weatherRepository, times(2)).getForecast(
            GEO_MODEL_1.latitude.toString(),
            GEO_MODEL_1.longitude.toString()
        )
        assertEquals(subject.viewState.value?.city, CITY_NAME_1)
        assertEquals(subject.viewState.value?.state is State.Content, true)
        assertEquals(subject.viewState.value?.state as State.Content, State.Content(FORECAST_MODEL))
    }

    @Test
    fun `successfully updates the city field`() = runTest {
        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(listOf(GEO_MODEL_1))
        `when`(weatherRepository.getForecast(anyString(), anyString())).thenReturn(FORECAST_MODEL)

        assertEquals(subject.viewState.value?.cityEditable, "")

        subject.processUiEvent(UiEvent.OnCitySearchEdit(CITY_NAME_2))

        assertEquals(subject.viewState.value?.cityEditable, CITY_NAME_2)
    }

    @Test
    fun `successful city update with weather data loading`() = runTest {
        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(listOf(GEO_MODEL_2))
        `when`(weatherRepository.getForecast(anyString(), anyString())).thenReturn(FORECAST_MODEL)

        assertEquals(subject.viewState.value?.city, CITY_NAME_1)

        subject.processUiEvent(UiEvent.OnCitySearchEdit(CITY_NAME_2))
        subject.processUiEvent(UiEvent.OnCitySearchButtonClicked)

        // Loads 2 times: first load is for CITY_NAME_1 during lazy initialization, the second is for CITY_NAME_2
        verify(weatherRepository, times(1)).getCoordinates(CITY_NAME_2)
        verify(weatherRepository, times(1)).getForecast(
            GEO_MODEL_2.latitude.toString(),
            GEO_MODEL_2.longitude.toString()
        )
        assertEquals(subject.viewState.value?.city, CITY_NAME_2)
        assertEquals(subject.viewState.value?.cityEditable, "")
        assertEquals(subject.viewState.value?.state is State.Content, true)
        assertEquals(subject.viewState.value?.state as State.Content, State.Content(FORECAST_MODEL))
    }

    @Test
    fun `specified city was not found`() = runTest {
        val exception = RuntimeException("Failed to connect")

        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_INCORRECT)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(emptyList())
        `when`(weatherRepository.getForecast(anyString(), anyString())).thenThrow(exception)

        subject.processUiEvent(UiEvent.OnUpdateButtonClicked)

        // Tries to load 2 times, once during lazy initialization, the second time after “pressing a button”
        verify(weatherRepository, times(2)).getCoordinates(CITY_NAME_INCORRECT)
        verify(weatherRepository, times(0)).getWeather(anyString(), anyString())
        assertEquals(subject.viewState.value?.city, CITY_NAME_INCORRECT)
        assertEquals(subject.viewState.value?.state is State.NotFound, true)
    }

    @Test
    fun `weather data load failed`() = runTest {
        val exception = RuntimeException("Failed to connect")

        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenThrow(exception)
        `when`(weatherRepository.getForecast(anyString(), anyString())).thenThrow(exception)

        subject.processUiEvent(UiEvent.OnUpdateButtonClicked)

        // Tries to load 2 times, once during lazy initialization, the second time after “pressing a button”
        verify(weatherRepository, times(2)).getCoordinates(CITY_NAME_1)
        verify(weatherRepository, times(0)).getForecast(anyString(), anyString())
        assertEquals(subject.viewState.value?.city, CITY_NAME_1)
        assertEquals(subject.viewState.value?.state is State.Error, true)
        assertEquals(subject.viewState.value?.state as State.Error, State.Error(exception))
    }
}