package com.leit.whattodoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(activity: Activity)

    @Query("SELECT * from activity ORDER BY description ASC")
    fun getActivities(): Flow<List<Activity>>

    @Query("SELECT * FROM activity WHERE id= :id")
    fun getActivity(id:Int):Flow<Activity>

    @Delete
    suspend fun deleteActivity(activity: Activity)


}