package com.leit.whattodoapp.network

data class Activity(
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    val key: Long,
    val accessibility: Double
)
