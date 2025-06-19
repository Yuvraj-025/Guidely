package com.yuvraj.travelguideapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuvraj.travelguideapp.R
import com.yuvraj.travelguideapp.databinding.RecyclerDetailBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.squareup.picasso.Picasso

class DetailAdapter(val context: Context, val imgList: List<TravelModel.ImageRoomlist>) :
    RecyclerView.Adapter<DetailAdapter.TravelDetailModelViewHolder>() {
    val TAG = "DetailAdapter"


    interface clickListener {
        fun imgClick(img_src: String)
    }

    lateinit var mListener: clickListener


    fun ClickListener(listener: clickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelDetailModelViewHolder {
        val travelBinding = RecyclerDetailBinding.inflate(LayoutInflater.from(parent.context))

        return TravelDetailModelViewHolder(travelBinding,mListener)
    }

    override fun onBindViewHolder(holder: TravelDetailModelViewHolder, position: Int) {

        val imgitem = imgList[position].url

        Picasso.get()
            .load(imgitem)
            .placeholder(R.drawable.ic_loader)
            .into(holder.detailSmallImage)

    }

    override fun getItemCount(): Int {
        return imgList.count()
    }
    inner class TravelDetailModelViewHolder(recyclerDetailBinding: RecyclerDetailBinding,listener: clickListener) :
        RecyclerView.ViewHolder(recyclerDetailBinding.root) {

        val detailSmallImage = recyclerDetailBinding.detailSmallImage
        init {
            itemView.setOnClickListener {
                imgList[absoluteAdapterPosition].url.let { it2->listener.imgClick(it2!!) }
            }
        }
    }
}


