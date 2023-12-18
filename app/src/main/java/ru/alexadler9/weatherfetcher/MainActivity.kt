package ru.alexadler9.weatherfetcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.alexadler9.weatherfetcher.base.hideKeyboard
import ru.alexadler9.weatherfetcher.databinding.ActivityMainBinding
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment.ForecastFragment
import ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.weatherfragment.WeatherFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.weatherFragment -> {
                    selectTab(WeatherFragment())
                }
                R.id.forecastFragment -> {
                    selectTab(ForecastFragment())
                }
                else -> {}
            }
            true
        }

        binding.etCitySearch.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
            }
        }
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, fragment)
        }
    }
}