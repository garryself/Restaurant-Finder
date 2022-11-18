package com.example.restaurantfinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantfinder.data.Restaurant

class RecyclerViewAdapter(private val mList: List<Restaurant>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var mContext : Context;
    // Inflate the card view and return the viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_card, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = mList[position]
        // Sets the holder info from the data
        val photoUrl = "https://maps.googleapis.com/maps/api/place/photo" +
                "?maxwidth=100&photo_reference=${restaurant.photoKey}" +
                "&key=${BuildConfig.MAPS_API_KEY}"

        Glide.with(mContext)
            .load(photoUrl)
            .centerCrop()
            .into(holder.imageView);

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