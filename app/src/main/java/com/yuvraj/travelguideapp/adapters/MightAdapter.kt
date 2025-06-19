package com.yuvraj.travelguideapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuvraj.travelguideapp.R
import com.yuvraj.travelguideapp.databinding.RecyclerItemMightBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.view.DetailActivity
import com.squareup.picasso.Picasso

class MightAdapter(val context: Context, val travelModel: List<TravelModel>) :
    RecyclerView.Adapter<MightAdapter.TravelMightModelViewHolder>() {
    val TAG = "MightAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelMightModelViewHolder {

        return TravelMightModelViewHolder(
            RecyclerItemMightBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: TravelMightModelViewHolder, position: Int) {

        val item = travelModel[position]

        holder.budgetTv.text = item.title


        Picasso.get()
            .load(item.images?.get(0)?.url)
            .placeholder(R.drawable.ic_loader)
            .error(R.drawable.ic_fill_home)
            .into(holder.mightTopImage)

        holder.itemView.setOnClickListener {
            Log.e(TAG, "click ${item.id}")
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra("id", item.id.toString())
            intent.putExtra("imgurl", item.images?.get(0)?.url)
            intent.putExtra("title", item.title)
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

    inner class TravelMightModelViewHolder(recyclerItemMightBinding: RecyclerItemMightBinding) :
        RecyclerView.ViewHolder(recyclerItemMightBinding.root) {

        val mightTopImage = recyclerItemMightBinding.mightTopImage
        val budgetTv = recyclerItemMightBinding.budgetTv
    }

}

