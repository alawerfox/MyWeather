package ru.avito.demo.myweather.ui.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.avito.demo.myweather.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private val viewModel by viewModel<CityViewModel>()
    private val navController by lazy { findNavController() }

    private lateinit var viewBinding: FragmentCityBinding

    private val cityAdapter = CityAdapter().apply {
        onClick = { viewModel.saveCurrentCity(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCityBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.searchFiled.addTextChangedListener {
            val query = it?.toString() ?: return@addTextChangedListener
            viewModel.search(query)
        }

        viewModel.back.observe(viewLifecycleOwner) {
            navController.popBackStack()
        }

        viewBinding.searchItem.apply {
            adapter = cityAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.city.observe(viewLifecycleOwner) {
            cityAdapter.geocodingItems = it
            cityAdapter.notifyDataSetChanged()
        }
    }
}