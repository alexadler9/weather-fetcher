package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import ru.alexadler9.weatherfetcher.data.PreferencesRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.data.WeatherRepository
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.WeatherInteractor
import ru.alexadler9.weatherfetcher.utility.*
import ru.alexadler9.weatherfetcher.utility.ext.CoroutinesTestExtension
import ru.alexadler9.weatherfetcher.utility.ext.InstantExecutorExtension

@ExtendWith(InstantExecutorExtension::class, CoroutinesTestExtension::class)
class WeatherFragmentViewModelTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var preferencesRepository: PreferencesRepository
    private lateinit var weatherInteractor: WeatherInteractor
    private lateinit var subject: WeatherViewModel

    @BeforeEach
    fun setUp() {
        weatherRepository = mock(WeatherRepository::class.java)
        preferencesRepository = mock(PreferencesRepository::class.java)
        weatherInteractor = WeatherInteractor(weatherRepository, preferencesRepository)
//        subject = WeatherViewModel(weatherInteractor)
    }

    /* runTest is a coroutine builder designed for testing. Use this to wrap any tests that include coroutines */
    @Test
    fun `successful loading of weather data`() = runTest {
        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(listOf(GEO_MODEL_1))
        `when`(weatherRepository.getWeather(anyString(), anyString())).thenReturn(WEATHER_MODEL)
        subject = WeatherViewModel(weatherInteractor)

        verify(weatherRepository, times(1)).getCoordinates(CITY_NAME_1)
        verify(weatherRepository, times(1)).getWeather(
            GEO_MODEL_1.latitude.toString(),
            GEO_MODEL_1.longitude.toString()
        )
        assertEquals(subject.viewState.value.city, CITY_NAME_1)
        assertEquals(subject.viewState.value.state is State.Content, true)
        assertEquals(subject.viewState.value.state as State.Content, State.Content(WEATHER_MODEL))
    }

    @Test
    fun `successfully updates the city field`() = runTest {
        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(listOf(GEO_MODEL_1))
        `when`(weatherRepository.getWeather(anyString(), anyString())).thenReturn(WEATHER_MODEL)
        subject = WeatherViewModel(weatherInteractor)

        assertEquals(subject.viewState.value.cityEditable, "")

        subject.processUiAction(UiAction.OnCitySearchEdited(CITY_NAME_2))

        assertEquals(subject.viewState.value.cityEditable, CITY_NAME_2)
    }

    @Test
    fun `successful city update with weather data loading`() = runTest {
        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(listOf(GEO_MODEL_2))
        `when`(weatherRepository.getWeather(anyString(), anyString())).thenReturn(WEATHER_MODEL)
        subject = WeatherViewModel(weatherInteractor)

        assertEquals(subject.viewState.value.city, CITY_NAME_1)

        subject.processUiAction(UiAction.OnCitySearchEdited(CITY_NAME_2))
        subject.processUiAction(UiAction.OnCitySearchButtonClicked)

        // Loads 2 times: first load is for CITY_NAME_1 in the init block, the second is for CITY_NAME_2
        verify(weatherRepository, times(1)).getCoordinates(CITY_NAME_2)
        verify(weatherRepository, times(1)).getWeather(
            GEO_MODEL_2.latitude.toString(),
            GEO_MODEL_2.longitude.toString()
        )
        assertEquals(subject.viewState.value.city, CITY_NAME_2)
        assertEquals(subject.viewState.value.cityEditable, "")
        assertEquals(subject.viewState.value.state is State.Content, true)
        assertEquals(subject.viewState.value.state as State.Content, State.Content(WEATHER_MODEL))
    }

    @Test
    fun `specified city was not found`() = runTest {
        val exception = RuntimeException("Failed to connect")

        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_INCORRECT)
        `when`(weatherRepository.getCoordinates(anyString())).thenReturn(emptyList())
        `when`(weatherRepository.getWeather(anyString(), anyString())).thenThrow(exception)
        subject = WeatherViewModel(weatherInteractor)

        verify(weatherRepository, times(1)).getCoordinates(CITY_NAME_INCORRECT)
        verify(weatherRepository, times(0)).getWeather(anyString(), anyString())
        assertEquals(subject.viewState.value.city, CITY_NAME_INCORRECT)
        assertEquals(subject.viewState.value.state is State.NotFound, true)
    }

    @Test
    fun `weather data load failed`() = runTest {
        val exception = RuntimeException("Failed to connect")

        `when`(preferencesRepository.getCity()).thenReturn(CITY_NAME_1)
        `when`(weatherRepository.getCoordinates(anyString())).thenThrow(exception)
        `when`(weatherRepository.getWeather(anyString(), anyString())).thenThrow(exception)
        subject = WeatherViewModel(weatherInteractor)

        verify(weatherRepository, times(1)).getCoordinates(CITY_NAME_1)
        verify(weatherRepository, times(0)).getWeather(anyString(), anyString())
        assertEquals(subject.viewState.value.city, CITY_NAME_1)
        assertEquals(subject.viewState.value.state is State.Error, true)
        assertEquals(subject.viewState.value.state as State.Error, State.Error(exception))
    }
}