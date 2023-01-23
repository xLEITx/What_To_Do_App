package com.leit.whattodoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.leit.whattodoapp.R
import com.leit.whattodoapp.WhatToDoApplication
import com.leit.whattodoapp.databinding.FragmentListActivityBinding
import com.leit.whattodoapp.model.RandomizeActivityViewModel

class ListActivityFragment : Fragment() {


    private val viewModel:RandomizeActivityViewModel by activityViewModels{
        RandomizeActivityViewModel.RandomizeActivityViewModelFactory(
            (activity?.application as WhatToDoApplication).database.activityDao()
        )
    }


    private lateinit var binding: FragmentListActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_activity,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = ActivityListAdapter{
            val action = ListActivityFragmentDirections.actionListActivityFragmentToDetailActivityFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        viewModel.allActivities.observe(this.viewLifecycleOwner){
            items ->
            items.let {
                adapter.submitList(it)
            }
        }
    }

}