package com.example.restaurantfinder

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
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
    private lateinit var editTextSearchTerm : EditText
    private lateinit var recyclerview : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerViewRestaurantList)
        recyclerview.layoutManager = LinearLayoutManager(this)

        btnSearch = findViewById(R.id.btnGetRestaurants)
        editTextSearchTerm = findViewById(R.id.editTextSearchTerm)

        // Trigger a search on action_done either in the soft keyboard,
        // or use the Search button below.
        editTextSearchTerm.setOnEditorActionListener { _, actionId: Int, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onDoSearch()
                false
            } else {
                false
            }
        }

        btnSearch.setOnClickListener {
                    onDoSearch()
                }
        }

    private fun onDoSearch() {
        val searchTerm = editTextSearchTerm.text.toString()
        if (!searchTerm.isEmpty()) {
            // Get a list of restaurants fr the current search term
            RestaurantService().getRestaurants(searchTerm) {
                // Send the restaurant list to the Recycler View
                recyclerview.adapter = RecyclerViewAdapter(it)
            }
        }
    }
}