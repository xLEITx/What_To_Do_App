package com.leit.whattodoapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leit.whattodoapp.network.Activity
import kotlinx.coroutines.launch

class RandomizeActivityViewModel : ViewModel() {
    private val _description = MutableLiveData<String>("Learn how to play a new sport")
    val description: LiveData<String> = _description

    private val _type = MutableLiveData<String>("recreational")
    val type: LiveData<String> = _type

    private val _participants = MutableLiveData<Int>(1)
    val participants: LiveData<Int> = _participants

    private val _price = MutableLiveData<Double>(0.1)
    val price: LiveData<Double> = _price

    private val _link = MutableLiveData<String>()
    val link: LiveData<String> = _link

    private val _key = MutableLiveData<Long>()
    val key: LiveData<Long> = _key

    private val _accessibility = MutableLiveData<Double>(0.2)
    val accessibility: LiveData<Double> = _accessibility


    init {
        getRandomActivity()
    }

    fun getActivity(type:String, difficult: String, price: String) {

        //TODO:Error handling if activity not found

        viewModelScope.launch {

            val activity: Activity = ActivityApi.retrofitService.getActivity(
                type,
                getMinAccessibility(difficult).toString(),
                getMaxAccessibility(difficult).toString(),
                getMinPrice(price).toString(),
                getMaxPrice(price).toString()
            )
            if(activity.error == "") {
                _description.value = activity.activity
                _type.value = activity.type
                _participants.value = activity.participants
                _price.value = activity.price
                _link.value = activity.link
                _key.value = activity.key
                _accessibility.value = activity.accessibility
            }
            else{
                _description.value = activity.error
            }


        }
    }
    private fun getRandomActivity(){
        viewModelScope.launch {
            val activity: Activity = ActivityApi.retrofitService.getRandomActivity()
            _description.value = activity.activity
            _type.value = activity.type
            _participants.value = activity.participants
            _price.value = activity.price
            _link.value = activity.link
            _key.value = activity.key
            _accessibility.value = activity.accessibility

        }
    }

    private fun getMinAccessibility(difficult: String):Double{
        return if (difficult.lowercase() == "very easy" ){
            0.0
        } else if (difficult.lowercase() == "easy"){
            0.25
        } else if (difficult.lowercase() == "hard"){
            0.5
        } else{
            0.75
        }
    }

    private fun getMaxAccessibility(difficult: String):Double{
        return if (difficult.lowercase() == "very easy" ){
            0.25
        } else if (difficult.lowercase() == "easy"){
            0.5
        } else if (difficult.lowercase() == "hard"){
            0.75
        } else{
            1.0
        }
    }

    private fun getMinPrice(price: String):Double{
        return if (price.lowercase() == "very cheap" ){
            0.0
        } else if (price.lowercase() == "cheap"){
            0.25
        } else if (price.lowercase() == "expensive"){
            0.5
        } else{
            0.75
        }
    }

    private fun getMaxPrice(price: String):Double{
        return if (price.lowercase() == "very cheap" ){
            0.25
        } else if (price.lowercase() == "cheap"){
            0.5
        } else if (price.lowercase() == "expensive"){
            0.75
        } else{
            1.0
        }
    }


}