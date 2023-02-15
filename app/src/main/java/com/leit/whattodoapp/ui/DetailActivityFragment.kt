package com.leit.whattodoapp.ui


import android.content.Intent
import android.net.Uri
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
    private val args: DetailActivityFragmentArgs by navArgs()

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
        binding.webButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(activityItem.link))
            startActivity(browserIntent)


        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteActivity(activityItem)
            this.findNavController().navigateUp()
        }

    }

    private fun bind(activity:Activity){
        binding.activityDescriptionTextView.text = activity.activity
        binding.activityTypeTextView.text = resources.getString(R.string.type_activity_text, activity.type)
        binding.activityAccessibilityTextView.text = resources.getString(R.string.accessibility_activity_text, viewModel.getFormattedValues(activity.accessibility))
        binding.activityParticipantsTextView.text = resources.getString(R.string.participants_activity_text, activity.participants.toString())
        binding.activityPriceTextView.text = resources.getString(R.string.price_activity_text, viewModel.getFormattedValues(activity.price))
        if (activity.link == ""){
            binding.webButton.visibility = View.GONE
        }

    }


}