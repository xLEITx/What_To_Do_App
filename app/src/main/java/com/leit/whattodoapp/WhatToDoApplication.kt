package com.leit.whattodoapp

import android.app.Application
import com.leit.whattodoapp.data.ActivityRoomDatabase

class WhatToDoApplication:Application() {

    val database:ActivityRoomDatabase by lazy{ActivityRoomDatabase.getDatabase(this)}

}