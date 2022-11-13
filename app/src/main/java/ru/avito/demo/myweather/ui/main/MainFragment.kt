package ru.avito.demo.myweather.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.avito.demo.myweather.R
import ru.avito.demo.myweather.databinding.FragmentMainBinding
import ru.avito.demo.myweather.ui.city.CityFragment

class MainFragment : Fragment() {

    private val viewModel by viewModel<MainViewModel>()
    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: FragmentMainBinding

    private val forecastAdapter = ForecastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.searchCity.setOnClickListener {
            navController.navigate(R.id.open_city)
        }

        viewBinding.forecast.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.city.observe(viewLifecycleOwner) {
            viewBinding.cityName.text = it
        }

        viewModel.weather.observe(viewLifecycleOwner) {
            val weatherMain = it.current.main

            viewBinding.temp.text = getString(R.string.temp, weatherMain.temp)
            viewBinding.tempRange.text =
                getString(R.string.temp_range, weatherMain.tempMin, weatherMain.tempMax)

            val weatherItem = it.current.weather.first()
            viewBinding.tempDescription.text = weatherItem.description

            Glide.with(viewBinding.icon).load(weatherItem.imageUrl).into(viewBinding.icon)

            forecastAdapter.forecast = it.forecast
            forecastAdapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }
}