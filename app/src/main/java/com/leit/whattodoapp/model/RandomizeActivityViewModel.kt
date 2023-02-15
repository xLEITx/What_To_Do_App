package com.leit.whattodoapp.model

import androidx.lifecycle.*
import com.leit.whattodoapp.data.ActivityDao
import com.leit.whattodoapp.network.Activity
import com.leit.whattodoapp.network.ActivityApi
import kotlinx.coroutines.launch

enum class Status{SUCCESS,LOADING,ERROR}

class RandomizeActivityViewModel(private val activityDao: ActivityDao) : ViewModel() {

    private val _status = MutableLiveData(Status.SUCCESS)
    val status: LiveData<Status> = _status

    private val _description = MutableLiveData("Learn how to play a new sport")
    val description: LiveData<String> = _description

    private val _type = MutableLiveData("recreational")
    val type: LiveData<String> = _type

    private val _participants = MutableLiveData(1)
    val participants: LiveData<Int> = _participants

    private val _price = MutableLiveData(0.1)
    val price: LiveData<Double> = _price

    private val _link = MutableLiveData("")
    val link: LiveData<String> = _link

    private val _accessibility = MutableLiveData(0.2)
    val accessibility: LiveData<Double> = _accessibility

    val allActivities:LiveData<List<com.leit.whattodoapp.data.Activity>> = activityDao.getActivities().asLiveData()

    init {
        getRandomActivity()
    }

    fun deleteActivity(activity: com.leit.whattodoapp.data.Activity){
        viewModelScope.launch {
            activityDao.deleteActivity(activity)
        }
    }

    fun retrieveActivity(id:Int):LiveData<com.leit.whattodoapp.data.Activity>{
        return activityDao.getActivity(id).asLiveData()
    }

    fun addNewActivity(){
        val newActivity = getNewActivityEntryForDb(
            description.value!!,
            type.value!!,
            participants.value!!,
            price.value!!,
            link.value!!,
            accessibility.value!!
        )
        insertActivity(newActivity)
    }

    fun getActivity(type:String, minAccessibility:Double, maxAccessibility:Double, minPrice: Double, maxPrice:Double) {


        viewModelScope.launch {

            _status.value = Status.LOADING
            try {
                val activity: Activity = ActivityApi.retrofitService.getActivity(
                    type,
                    minAccessibility.toString(),
                    maxAccessibility.toString(),
                    minPrice.toString(),
                    maxPrice.toString()
                )
                if (activity.error == "") {
                    _description.value = activity.activity
                    _type.value = activity.type
                    _participants.value = activity.participants
                    _price.value = activity.price
                    _link.value = activity.link
                    _accessibility.value = activity.accessibility
                    _status.value = Status.SUCCESS

                } else {
                    _status.value = Status.ERROR
                    _description.value = activity.error
                }

            }catch (e: Exception){
                _status.value = Status.ERROR
                _description.value = "No internet connection"
            }
        }
    }
    fun getFormattedValues(value: Double):String{
        return (value * 100).toInt().toString()
    }

    private fun getRandomActivity(){
        viewModelScope.launch {
            _status.value = Status.LOADING
            try {
                val activity: Activity = ActivityApi.retrofitService.getRandomActivity()
                _description.value = activity.activity
                _type.value = activity.type
                _participants.value = activity.participants
                _price.value = activity.price
                _link.value = activity.link
                _accessibility.value = activity.accessibility
                _status.value = Status.SUCCESS
            }catch (e:Exception){
                _status.value = Status.ERROR
                _description.value = "No internet connection"
            }

        }
    }

    private fun insertActivity(activity: com.leit.whattodoapp.data.Activity){
        viewModelScope.launch {
            activityDao.insert(activity)
        }
    }

    private fun getNewActivityEntryForDb(
        activityDescription:String,
        type: String,
        participants:Int,
        price: Double,
        link:String,
        accessibility: Double ): com.leit.whattodoapp.data.Activity {

        return com.leit.whattodoapp.data.Activity(
            activity = activityDescription,
            type = type,
            participants = participants,
            price = price,
            link = link,
            accessibility = accessibility)

    }


    class RandomizeActivityViewModelFactory(private val activityDao: ActivityDao) :ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(RandomizeActivityViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return RandomizeActivityViewModel(activityDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

}