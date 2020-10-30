package com.example.zomatosearch.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zomatosearch.R
import com.example.zomatosearch.network.data.hotel
import com.squareup.picasso.Picasso

class searchAdapter(val hotels:List<hotel>,val ctx: Context) : RecyclerView.Adapter<searchAdapter.userholder>()  {



    class userholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(hotels: hotel) {

            val name = itemView.findViewById(R.id.name) as TextView
            val reviews = itemView.findViewById(R.id.review) as TextView
            val locality = itemView.findViewById(R.id.locality) as TextView
            val hot_pic  = itemView.findViewById(R.id.hotelpic) as ImageView
           if (!TextUtils.isEmpty(hotels.pic)) {
               Picasso.get().load(hotels.pic).placeholder(R.drawable.ic_baseline_restaurant_24).into(hot_pic)
           }else{
               hot_pic.setBackgroundResource(R.drawable.ic_baseline_restaurant_24)
           }
            name.text=hotels.name
            reviews.text=hotels.review
            locality.text=hotels.locality
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.hotels_design, parent, false)
        return userholder(v)
    }

    override fun onBindViewHolder(holder: userholder, position: Int) {
        holder.bindItems(hotels[position])
    }

    override fun getItemCount(): Int {
        return hotels.size
    }
}