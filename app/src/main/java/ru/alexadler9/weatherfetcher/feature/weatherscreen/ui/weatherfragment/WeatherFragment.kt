package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexadler9.weatherfetcher.databinding.FragmentWeatherBinding
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.WeatherScreenContract

class WeatherFragment : Fragment(), WeatherScreenContract.Fragment {

    //    private val viewModel: WeatherFragmentViewModel by viewModel {
//        parametersOf(requireAppPreferences().getCity())
//    }
    private val viewModel: WeatherFragmentViewModel by viewModel()

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        binding.fabWeatherFetch.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnUpdateButtonClicked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: ViewState) {
        val activityParent = activity
        if (activityParent is WeatherScreenContract.Activity) {
            activityParent.citySearchEditSetText(viewState.cityEditable)
            activityParent.citySearchButtonSetEnabled(viewState.cityEditable.isNotEmpty())
        }
        with(binding) {
            when (viewState.state) {
                is State.Load -> {
                    layoutError.isVisible = false
                    layoutNotFound.isVisible = false
                    pbWeather.isVisible = true
                    fabWeatherFetch.isEnabled = false
                    layoutWeather.isVisible = false
                }

                is State.Content -> {
                    layoutError.isVisible = false
                    layoutNotFound.isVisible = false
                    pbWeather.isVisible = false
                    fabWeatherFetch.isEnabled = true
                    layoutWeather.isVisible = true
                    val weather = viewState.state.weatherModel
                    tvWeather.text = weather.toString()
                }

                is State.Error,
                is State.NotFound -> {
                    layoutError.isVisible = viewState.state is State.Error
                    layoutNotFound.isVisible = viewState.state is State.NotFound
                    pbWeather.isVisible = false
                    fabWeatherFetch.isEnabled = true
                    layoutWeather.isVisible = false
                }
            }
        }
    }

    override fun onCitySearchEditChanged(text: String) {
        viewModel.processUiEvent(UiEvent.OnCitySearchEdit(text))
    }

    override fun onCitySearchButtonClicked() {
        viewModel.processUiEvent(UiEvent.OnCitySearchButtonClicked)
    }
}