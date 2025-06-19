package com.yuvraj.travelguideapp.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuvraj.travelguideapp.R
import com.yuvraj.travelguideapp.databinding.RecyclerItemTopPickBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.view.DetailActivity
import com.squareup.picasso.Picasso

class TopPickAdapter(val context: Context, val travelModel: List<TravelModel>) :
    RecyclerView.Adapter<TopPickAdapter.TravelTopPickModelViewHolder>() {
    val TAG = "TopPickAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelTopPickModelViewHolder {
        return TravelTopPickModelViewHolder(RecyclerItemTopPickBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TravelTopPickModelViewHolder, position: Int) {

        val item = travelModel[position]

        holder.title.text = item.title
        holder.info.text = item.description

        Picasso.get()
            .load(item.images?.get(0)?.url)
            .placeholder(R.drawable.ic_loader)
            .into(holder.topBlogImage)



        holder.itemView.setOnClickListener {
            Log.e(TAG, "click ${item.id}")
            val intent = Intent(context, DetailActivity::class.java)

            intent.putExtra("id",item.id.toString())
            intent.putExtra("imgurl",item.images?.get(0)?.url)
            intent.putExtra("title",item.title)
            intent.putExtra("description",item.description)
            intent.putExtra("country",item.country)
            intent.putExtra("category",item.category)
            intent.putExtra("bookmark",item.isBookmark)

            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return travelModel.count()
    }

    inner class TravelTopPickModelViewHolder(recyclerItemTopPickBinding: RecyclerItemTopPickBinding) :
        RecyclerView.ViewHolder(recyclerItemTopPickBinding.root) {


        val topBlogImage = recyclerItemTopPickBinding.topBlogImage
        val title = recyclerItemTopPickBinding.categoryTv
        val info = recyclerItemTopPickBinding.informationTv

    }

}