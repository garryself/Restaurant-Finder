package com.example.restaurantfinder.network

import com.example.restaurantfinder.BuildConfig
import com.example.restaurantfinder.data.Restaurant
import com.example.restaurantfinder.network.request.PlacesRequest
import com.example.restaurantfinder.network.response.PlacesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestaurantService {
    private val baseUrl = "https://maps.googleapis.com/maps/api/place/"
    private val placeType = "restaurant"
    private val limit = 10

    // The list of restaurants in the format we require
    val restaurantList = ArrayList<Restaurant>()

    private fun getPlacesRequest() : PlacesRequest {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY))
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PlacesRequest::class.java)
    }

    fun getRestaurants(place: String, callback: (result: List<Restaurant>) -> Unit) {

//        // Mock data. Uncomment for testing to avoid hitting Places API repeatedly.
//        restaurantList.add(Restaurant("North Italia", 4.5, "2957 Michelson Dr, Irvine, CA 92612, United States", "AW30NDyswVS17UBaJ1ePAMaY75Hqa4adk39QD4QgqpP95ZNvq4kAS3BitsudCQXackSRlMVp5U8AkdkUTT3IXenMMc6EFGeO59CLmc3WhDrMb6TQWV5Kbsqn5jMV0yKyZXR4f2oLVygmaNirkQVF6tNJHTNeyVEoWySJLgoOh3d7oLOFZwKa"))
//        restaurantList.add(Restaurant("Porch & Swing", 4.1, "2010 Main St Suite 170, Irvine, CA 92614, United States", "AW30NDxTdxgt1URVCx6QsCxCfGXkzZnY1_urooUMgAVTbotxFQr_8ShsyZDwi4N0PFWx089PwHft-qgdFCY8vT7pNaVRiRPGi7QzYX9-o1rz44Z1g1r6DGoLAM8emb85AKfyoLcG5w3hoU0d7A3-qH1AC2VTYxrQKr1x8CMwaA1gPJbMzRSV"))
//        callback.invoke(restaurantList)
//        return

        getPlacesRequest()
            .getRestaurants(
                BuildConfig.MAPS_API_KEY,
                place, placeType
            )
            .enqueue(object : Callback<PlacesResponse> {
                override fun onResponse(
                    call: Call<PlacesResponse>,
                    response: Response<PlacesResponse>
                ) {
                    if (response.code() == 200) {
                        // parse the photos
                        processResponse(response, limit)
                    }
                    callback.invoke(restaurantList) // empty or valid list
                }

                override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                    callback.invoke(restaurantList) // empty list
                }
            })
    }

    // Translates the response from the server (a large JSON document)
    // to a lightweight list the application needs
    private fun processResponse(response: Response<PlacesResponse>, limit : Int) {
        val body = response.body()
        if (body != null) {
            for (result in body.results) {
                restaurantList.add(Restaurant(
                    result.name ?: "",
                    result.rating ?: 0.0,
                    result.formattedAddress ?: "",
                    result.photos[0].photoReference ?: ""))
                if (restaurantList.size >= limit) break
            }
        }
    }
}