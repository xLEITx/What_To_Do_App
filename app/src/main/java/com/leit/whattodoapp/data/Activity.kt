package com.leit.whattodoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity")
data class Activity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo(name = "description")
    val activity:String,
    @ColumnInfo(name = "type")
    val type:String,
    @ColumnInfo(name = "participants")
    val participants:Int,
    @ColumnInfo(name = "price")
    val price:Double,
    @ColumnInfo(name = "link")
    val link:String,
    @ColumnInfo(name = "accessibility")
    val accessibility:Double
)
