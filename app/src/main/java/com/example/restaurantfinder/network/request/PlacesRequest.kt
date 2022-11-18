package com.example.restaurantfinder.network.request

import com.example.restaurantfinder.network.response.PlacesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PlacesRequest {
    @Headers("Cache-Control:public, max-age=31536000")
    @GET("textsearch/json")
    fun getRestaurants(@Query("key") key: String,
                       @Query("query") query: String,
                       @Query("type") type: String) : Call<PlacesResponse>
}