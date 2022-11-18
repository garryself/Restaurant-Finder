package com.example.restaurantfinder.network.response

import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("results")
    var results: ArrayList<Results> = arrayListOf(),

    @SerializedName("status")
    var status: String? = null
)