package com.example.restaurantfinder.network.response

import com.example.example.Photos
import com.google.gson.annotations.SerializedName


data class Results(
    @SerializedName("formatted_address")
    var formattedAddress: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("photos")
    var photos: ArrayList<Photos> = arrayListOf(),
    @SerializedName("place_id")
    var placeId: String? = null,
    @SerializedName("rating")
    var rating: Double? = null,
    @SerializedName("user_ratings_total")
    var userRatingsTotal: Int? = null
)