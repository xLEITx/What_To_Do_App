package com.leit.whattodoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.leit.whattodoapp.databinding.FragmentDetailActivityBinding
import com.leit.whattodoapp.model.RandomizeActivityViewModel

class DetailActivityFragment : Fragment() {
    private var binding: FragmentDetailActivityBinding? = null
    private val  sharedViewModel: RandomizeActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentDetailActivityBinding.inflate(
            inflater,
            container,
            false
        )
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
            detailActivityFragment = this@DetailActivityFragment
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}