package com.leit.whattodoapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RandomizeActivityViewModel: ViewModel() {
    private val _description = MutableLiveData<String>("Learn how to play a new sport")
    val description: LiveData<String> = _description

    private val _accessibility = MutableLiveData<Double>(0.2)
    val accessibility: LiveData<Double> = _accessibility

    private val _type = MutableLiveData<String>("recreational")
    val type: LiveData<String> = _type

    private val _participants = MutableLiveData<Int>(1)
    val participants: LiveData<Int> = _participants

    private val _price = MutableLiveData<Double>(0.1)
    val price: LiveData<Double> = _price

}