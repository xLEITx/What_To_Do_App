package com.leit.whattodoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.leit.whattodoapp.R
import com.leit.whattodoapp.databinding.FragmentRandomizeActivityBinding
import com.leit.whattodoapp.model.RandomizeActivityViewModel


class RandomizeActivityFragment : Fragment() {
    private val viewModel: RandomizeActivityViewModel by activityViewModels()

    private lateinit var binding: FragmentRandomizeActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    }


}