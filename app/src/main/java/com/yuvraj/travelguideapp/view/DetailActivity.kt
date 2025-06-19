package com.yuvraj.travelguideapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuvraj.travelguideapp.R
import com.yuvraj.travelguideapp.adapters.DetailAdapter
import com.yuvraj.travelguideapp.databinding.ActivityDetailBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.room.TravelDatabase
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    val TAG = "DetailActivity"

    private lateinit var binding: ActivityDetailBinding
    private var detailAdapter: DetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val desc = intent.getStringExtra("description")
        val country = intent.getStringExtra("country")
        val imgurl = intent.getStringExtra("imgurl")

        Picasso.get().load(imgurl).into(binding.topBg)
        binding.topTv.text = "$title"
        binding.descriptionTv.text = "$desc"
        binding.locationTv.text = "$country"

        binding.arrow1.setOnClickListener {

            if (chosenImg != "") {
                FullscreenImageDialog(this, chosenImg).show()

            }


        }

        val data = TravelDatabase.getDatabase(this@DetailActivity)


        data.roomDao().getTravel().forEach { travelModel ->
            travelModel.isBookmark
            Log.e(TAG, "click ${travelModel.id}")

            if (travelModel.id.toString() == id) {
                travelModel?.let {
                    setupRecy(travelModel.images!!)
                }
            }
        }

        data.roomDao().getTravel().forEach { newList ->
            if (newList.id.toString() == id) {
                binding.bookmarkButton.setOnClickListener {
                    val data = TravelDatabase.getDatabase(this@DetailActivity)
                    if (newList.isBookmark == true) {
                        Log.e(TAG, "click true ${newList.id} ${newList.isBookmark}")
                        newList.isBookmark = false
                        data.roomDao().updateBookmark(newList.id, false)
                        binding.bookmarkTv.text = "Add Bookmark"

                    } else {
                        newList.isBookmark = true
                        Log.e(TAG, "click false ${newList.id}  ${newList.isBookmark}")
                        data.roomDao().updateBookmark(newList.id, true)
                        binding.bookmarkTv.text = "Remove Bookmark"

                    }
                }
            }
        }
        setContentView(binding.root)

    }


    var chosenImg = ""
    fun setupRecy(newList: List<TravelModel.ImageRoomlist>) {
        binding.gridsDetail.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        detailAdapter = DetailAdapter(this@DetailActivity, newList)
        binding.gridsDetail.adapter = detailAdapter


        chosenImg = newList[0].url.toString()
        detailAdapter!!.ClickListener(object : DetailAdapter.clickListener {
            override fun imgClick(img_src: String) {
                chosenImg = img_src
                Picasso.get()
                    .load(img_src)
                    .placeholder(R.drawable.ic_loader)
                    .into(binding.topBg)
            }
        })
    }
}



