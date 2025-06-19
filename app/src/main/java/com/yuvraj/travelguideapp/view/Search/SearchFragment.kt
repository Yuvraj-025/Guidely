package com.yuvraj.travelguideapp.view.Search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.yuvraj.travelguideapp.adapters.*
import com.yuvraj.travelguideapp.databinding.FragmentSearchBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.room.TravelDatabase


class SearchFragment : Fragment() {
    private var topDestAdapter: TopDestinationsAdapter? = null
    private var nearbyAdapter: NearbyAdapter? = null
    private var travelModel: List<TravelModel>? = null
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.editText1.doOnTextChanged { text, start, before, count ->

            val filterList = ArrayList<TravelModel>()
            val topDestList = travelModel?.filter {
                it.category == "topdestination"
            }

            setupRecyclerViewTopDest(topDestList as ArrayList<TravelModel>)

            topDestList?.forEach { item ->

                if (item.title.trim().contains(text.toString().trim())) {
                    filterList.add(item)
                    if (filterList.size > 0) {
                        setupRecyclerViewTopDest(filterList)
                    }
                }
            }

            if (text.toString() == "") {
                travelModel?.let { activity ->
                    val topDestList = travelModel!!.filter {
                        it.category == "topdestination"
                    }
                    setupRecyclerViewTopDest(topDestList as ArrayList<TravelModel>)
                }
            }
        }
        return binding.root
    }


    val TAG = "SearchFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { activity ->

            try {
                val data = TravelDatabase.getDatabase(activity)
                travelModel = data.roomDao().getTravel()
                travelModel?.let { travelModel ->

                    val topDestList = travelModel.filter {
                        it.category == "topdestination"
                    }
                    setupRecyclerViewTopDest(topDestList)
                    val nearbyList = travelModel.filter {
                        it.category == "nearby"
                    }
                    setupRecyclerViewNearby(nearbyList)
                }
            } catch (e: Exception) {
                Log.e(TAG, "onViewCreated: ex ${e.message}")
            }
        }
    }



    private fun setupRecyclerViewTopDest(newList: List<TravelModel>) {

        activity?.let { activity ->

            binding.grids2.layoutManager = GridLayoutManager(
                activity.applicationContext,
                1,
                GridLayoutManager.HORIZONTAL,
                false

            )


            topDestAdapter = TopDestinationsAdapter(activity, newList)
            binding.grids2.adapter = topDestAdapter
        }
    }

    private fun setupRecyclerViewNearby(newList: List<TravelModel>) {

        activity?.let { activity ->
            binding.grids3.layoutManager = GridLayoutManager(
                activity.applicationContext,
                1,
                GridLayoutManager.VERTICAL,
                false
            )

            nearbyAdapter = NearbyAdapter(activity, newList)
            binding.grids3.adapter = nearbyAdapter
        }
    }
}

