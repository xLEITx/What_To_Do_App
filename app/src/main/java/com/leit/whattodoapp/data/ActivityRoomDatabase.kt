package com.leit.whattodoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Activity::class], version = 1, exportSchema = false)
abstract class ActivityRoomDatabase:RoomDatabase() {
    abstract fun activityDao(): ActivityDao

    companion object{
        @Volatile
        private var INSTANCE: ActivityRoomDatabase? = null
        fun getDatabase(context: Context):ActivityRoomDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ActivityRoomDatabase::class.java,
                    "activity_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }

}