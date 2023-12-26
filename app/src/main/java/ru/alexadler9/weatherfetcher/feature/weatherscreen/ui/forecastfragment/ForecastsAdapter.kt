package ru.alexadler9.weatherfetcher.feature.weatherscreen.ui.forecastfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.alexadler9.weatherfetcher.R
import ru.alexadler9.weatherfetcher.databinding.ItemForecastBinding
import ru.alexadler9.weatherfetcher.feature.weatherscreen.domain.model.ForecastWeatherModel

class ForecastsAdapter : RecyclerView.Adapter<ForecastsAdapter.ForecastViewHolder>() {

    var data = listOf<ForecastWeatherModel>()
        // No need to use DiffUtil
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ForecastViewHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ForecastWeatherModel) {
            with(binding) {
                tvDayOfWeek.text = item.dayOfWeek.uppercase()
                tvDate.text = item.date
                tvDayTemp.text = root.context.getString(
                    R.string.temperature_value,
                    item.tempDay.toFloat()
                ).replace(',', '.')
                tvNightTemp.text = root.context.getString(
                    R.string.temperature_value,
                    item.tempNight.toFloat()
                ).replace(',', '.')
                Glide.with(root.context)
                    .load(item.details.first().icon)
                    .circleCrop()
                    .placeholder(R.drawable.ic_baseline_image_search_24)
                    .into(ivIcon)
            }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): ForecastViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemForecastBinding.inflate(layoutInflater, parent, false)
                return ForecastViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size
}