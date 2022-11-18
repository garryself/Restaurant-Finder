package com.example.restaurantfinder

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantfinder.network.RestaurantService


//Using the google maps api, users should be able to search in an input field
// for a particular location/area (example irvine) and be shown up to
// 10 restaurants of that area. This should be displayed in some sort of
// listing. In each listing record show the name of the restaurant,
// the rating of the restaurant, and the address of the restaurant.
//
//Example: User searches Irvine
//
//Olive Garden    3.5    156 spectrum ave
//Stonefire grill   3.0    175 olive grove ave
//
//For bonus marks. Display the picture for each of the restaurants as well

class MainActivity : AppCompatActivity() {

    private lateinit var btnSearch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerViewRestaurantList)
        recyclerview.layoutManager = LinearLayoutManager(this)

        btnSearch = findViewById<Button>(R.id.btnGetRestaurants)

        btnSearch.setOnClickListener {
                val searchTerm = btnSearch.text.toString()
                if (!searchTerm.isEmpty()) {
                    // Get a list of restaurants fr the current search term
                    RestaurantService().getRestaurants(searchTerm) {
                        // Send the restaurant list to the Recycler View
                        recyclerview.adapter = RecyclerViewAdapter(it)
                    }
                }
        }
    }
}