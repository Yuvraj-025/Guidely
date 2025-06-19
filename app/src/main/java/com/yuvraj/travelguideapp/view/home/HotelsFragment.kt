package com.yuvraj.travelguideapp.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuvraj.travelguideapp.adapters.RecyclerAdapter
import com.yuvraj.travelguideapp.data.service.TravelApi
import com.yuvraj.travelguideapp.databinding.FragmentHotelsBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelsFragment : Fragment() {
    private val travelApi = TravelApi()
    private var recyclerAdapter: RecyclerAdapter? = null
    private var travelModel: ArrayList<TravelModel>? = null
    private lateinit var binding: FragmentHotelsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHotelsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        activity?.let{
                activity->
            val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
                activity.applicationContext,
                1,
                GridLayoutManager.HORIZONTAL,
                false
            )
            binding.grids.layoutManager = layoutManager
        }
    }

    fun getData() {
        val call = travelApi.getTravelData()
        call.enqueue(object : Callback<List<TravelModel>> {
            override fun onResponse(
                call: Call<List<TravelModel>>,
                response: Response<List<TravelModel>>
            ) {
                if (response.isSuccessful) {
                    response.body().let {responseList ->
                        travelModel = ArrayList(responseList)
                        travelModel?.let {travelModel ->
                            activity?.let { activity->
                                val hotelList = travelModel.filter {
                                    it.category == "hotel"

                                }

                                setupRecyclerViewHome(hotelList as ArrayList<TravelModel> )
                                binding.grids.setHasFixedSize(true)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<TravelModel>>, t: Throwable) {
                Log.v("API Failure", t.message.toString())
            }
        })
    }

    private fun setupRecyclerViewHome(newList: ArrayList<TravelModel>) {

        activity?.let { activity ->
            recyclerAdapter = RecyclerAdapter(activity, newList)
            binding.grids.adapter = recyclerAdapter
        }

    }
}