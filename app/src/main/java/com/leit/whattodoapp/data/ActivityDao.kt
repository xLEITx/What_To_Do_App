package com.leit.whattodoapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(activity: Activity)


}