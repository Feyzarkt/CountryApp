package com.huawei.countryapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.huawei.countryapp.R
import com.huawei.countryapp.adapter.CountryAdapter
import com.huawei.countryapp.util.ApplicationViewModelFactory
import com.huawei.countryapp.databinding.FragmentHomeBinding
import com.huawei.countryapp.viewmodel.MainViewModel


class HomeFragment : Fragment() {

    //private lateinit var viewModel : MainViewModel
    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }

    private lateinit var binding : FragmentHomeBinding
    /*private var countryAdapter = CountryAdapter(arrayListOf()) { position ->
        //findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
    }*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container, false)
        //viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.countryRV.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getDataFromAPI()
        setObservers()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setObservers(){
        viewModel.countryData.observe(viewLifecycleOwner) { list ->
            val countryAdapter = CountryAdapter(arrayListOf()) { position ->
                //findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(list[position]))
            }
            binding.countryRV.adapter = countryAdapter
            countryAdapter.updateList(list)
            viewModel.insertAll(list)
        }

        viewModel.countryLoad.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.loadingPB.visibility = View.VISIBLE
            } else {
                binding.loadingPB.visibility = View.GONE
            }
        }

        viewModel.countryError.observe(viewLifecycleOwner) { error ->
            if (error) {
                binding.errorTV.visibility = View.VISIBLE
            } else {
                binding.errorTV.visibility = View.GONE
            }
        }

    }
}