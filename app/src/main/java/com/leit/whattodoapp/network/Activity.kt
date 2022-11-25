package com.leit.whattodoapp.network

data class Activity(
    val activity: String = "",
    val type: String = "",
    val participants: Int = 0,
    val price: Double = 0.0,
    val link: String = "",
    val key: Long = 0,
    val accessibility: Double = 0.0,
    val error: String = ""
)
