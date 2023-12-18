package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexadler9.weatherfetcher.MainActivity
import ru.alexadler9.weatherfetcher.base.toEditable
import ru.alexadler9.weatherfetcher.databinding.FragmentForecastBinding

class ForecastFragment : Fragment() {

    //    private val viewModel: ForecastFragmentViewModel by viewModel {
//        parametersOf(requireAppPreferences().getCity())
//    }
    private val viewModel: ForecastFragmentViewModel by viewModel()

    private var _binding: FragmentForecastBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        binding.fabWeatherFetch.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnButtonClicked)
        }

        with((activity as MainActivity).binding) {
            etCitySearch.doAfterTextChanged {
                viewModel.processUiEvent(UiEvent.OnCitySearchEdit(it.toString()))
            }

            ivCitySearch.setOnClickListener {
                viewModel.processUiEvent(UiEvent.OnCitySearchButtonClicked)
                etCitySearch.clearFocus()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: ViewState) {
        with((activity as MainActivity).binding) {
            if (etCitySearch.text.toString() != viewState.cityEditable) {
                etCitySearch.text = viewState.cityEditable.toEditable()
            }
            ivCitySearch.isEnabled = etCitySearch.text.isNotEmpty()
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
                    val forecast = viewState.state.forecastModel
                    tvWeather.text = forecast.toString()
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
}