package com.leit.whattodoapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.leit.whattodoapp.R
import com.leit.whattodoapp.WhatToDoApplication
import com.leit.whattodoapp.databinding.FragmentRandomizeActivityBinding
import com.leit.whattodoapp.model.RandomizeActivityViewModel


class RandomizeActivityFragment : Fragment() {
    private val viewModel: RandomizeActivityViewModel by activityViewModels{
        RandomizeActivityViewModel.RandomizeActivityViewModelFactory(
            (activity?.application as WhatToDoApplication).database.activityDao()
        )
    }

    private lateinit var binding: FragmentRandomizeActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_randomize_activity,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.randomizeButton.setOnClickListener {
            viewModel.getActivity(
                binding.typeSpinner.selectedItem.toString().lowercase(),
                binding.accessibilitySpinner.selectedItem.toString(),
                binding.priceSpinner.selectedItem.toString()
            )


        }
        binding.addInListButton.setOnClickListener {
            viewModel.addNewActivity()
            Toast.makeText(context,R.string.db_add_toast_text, Toast.LENGTH_SHORT).show()
        }

        binding.activityDescriptionTextView.setOnClickListener {
            if (viewModel.link.value != ""){
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.link.value))
                startActivity(browserIntent)
            }

        }

    }


}