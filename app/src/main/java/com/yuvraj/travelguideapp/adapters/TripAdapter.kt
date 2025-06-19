package com.yuvraj.travelguideapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuvraj.travelguideapp.R
import com.yuvraj.travelguideapp.databinding.RecyclerTripBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.view.DetailActivity
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class TripAdapter(val context: Context, var travelModel: List<TravelModel>) :
    RecyclerView.Adapter<TripAdapter.TravelRoomModelViewHolder>() {
    val TAG = "NearbyAdapter"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelRoomModelViewHolder {

        return TravelRoomModelViewHolder(
            RecyclerTripBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: TravelRoomModelViewHolder, position: Int) {

        val item = travelModel[position]
        val imgitem = travelModel[position].images?.get(0)?.url
        var preferences = context.getSharedPreferences("tripdata",Context.MODE_PRIVATE)
        var prefDate = preferences.getString("date","")
        val sdf = SimpleDateFormat("dd/MM/yyyy")


        Picasso.get()
            .load(imgitem)
            .placeholder(R.drawable.ic_loader)
            .into(holder.centerImage)

        holder.centerName.text = item.title
        holder.centerDate.text = prefDate.toString()



        holder.itemView.setOnClickListener {
            Log.e(TAG, "click ${item.id}")
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra("id", item.id.toString())
            intent.putExtra("title", item.title)
            intent.putExtra("imgurl", item.images?.get(0)?.url)
            intent.putExtra("description", item.description)
            intent.putExtra("country", item.country)
            intent.putExtra("category", item.category)
            intent.putExtra("bookmark",item.isBookmark)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return travelModel.count()
    }


    inner class TravelRoomModelViewHolder(recyclerTripBinding: RecyclerTripBinding) :
        RecyclerView.ViewHolder(recyclerTripBinding.root) {

        val centerImage = recyclerTripBinding.nearbyImage
        val centerName = recyclerTripBinding.centerTv1
        val centerDate = recyclerTripBinding.centerTv2
    }
}