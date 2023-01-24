package com.leit.whattodoapp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.leit.whattodoapp.R
import com.leit.whattodoapp.WhatToDoApplication
import com.leit.whattodoapp.data.Activity
import com.leit.whattodoapp.databinding.FragmentDetailActivityBinding
import com.leit.whattodoapp.model.RandomizeActivityViewModel


class DetailActivityFragment : Fragment() {

    private lateinit var activityItem:Activity
    private lateinit var binding:FragmentDetailActivityBinding
    val args: DetailActivityFragmentArgs by navArgs()

    private val viewModel: RandomizeActivityViewModel by activityViewModels {
        RandomizeActivityViewModel.RandomizeActivityViewModelFactory(
            (activity?.application as WhatToDoApplication).database.activityDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_activity,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.itemId
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.retrieveActivity(id).observe(this.viewLifecycleOwner){ selectedItem ->
            activityItem = selectedItem
            bind(activityItem)

        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteActivity(activityItem)
            this.findNavController().navigateUp()
        }

    }

    private fun bind(activity:Activity){
        binding.activityDescriptionTextView.text = activity.activity
        binding.activityTypeTextView.text = activity.type
        binding.activityAccessibilityTextView.text = activity.accessibility.toString()
        binding.activityParticipantsTextView.text = activity.participants.toString()
        binding.activityPriceTextView.text = activity.price.toString()
    }


}