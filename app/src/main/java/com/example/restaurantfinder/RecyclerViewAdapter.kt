package com.example.restaurantfinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantfinder.data.Restaurant

class RecyclerViewAdapter(private val mList: List<Restaurant>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // Inflate the card view and return the viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_card, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = mList[position]
        // Sets the holder info from the data
        holder.name.text = restaurant.name
        holder.rating.text = restaurant.rating.toString()
        holder.address.text = restaurant.address
    }

    // The size of the data list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds views for the four items on the card.
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.photo)
        val name: TextView = itemView.findViewById(R.id.name)
        val rating: TextView = itemView.findViewById(R.id.rating)
        val address: TextView = itemView.findViewById(R.id.address)
    }
}