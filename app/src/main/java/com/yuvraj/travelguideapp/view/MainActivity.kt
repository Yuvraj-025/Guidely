package com.yuvraj.travelguideapp.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yuvraj.travelguideapp.R
import com.yuvraj.travelguideapp.data.service.TravelApi
import com.yuvraj.travelguideapp.databinding.ActivityMainBinding
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.room.TravelDatabase
import com.yuvraj.travelguideapp.view.Search.SearchFragment
import com.yuvraj.travelguideapp.view.Trip.TripFragment
import com.yuvraj.travelguideapp.view.guide.GuideFragment
import com.yuvraj.travelguideapp.view.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var preferences: SharedPreferences
    private lateinit var bottomNavView: BottomNavigationView
    private val TAG = "MainActivity"
    val homeFragment = HomeFragment()
    val guideFragment = GuideFragment()
    val searchFragment = SearchFragment()
    val tripFragment = TripFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = getSharedPreferences("tripdata", MODE_PRIVATE)
        getData()
        bottomNavView = binding.bottomNavigation
        setCurrentFragment(homeFragment)
        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                }
                R.id.nav_search -> {
                    setCurrentFragment(searchFragment)
                }
                R.id.nav_trip -> {
                    setCurrentFragment(tripFragment)
                }
                R.id.nav_guide -> {
                    setCurrentFragment(guideFragment)
                }
            }
            true
        }

    }

    private val travelApi = TravelApi()

    fun getData() {
        Log.e(TAG, "getData: ", )
        val call = travelApi.getTravelData()
        call.enqueue(object : Callback<List<TravelModel>> {
            override fun onResponse(
                call: Call<List<TravelModel>>,
                response: Response<List<TravelModel>>
            ) {
                if (response.isSuccessful) {
                    response.body().let { travelList ->
                        travelList?.let {
                            travelList.forEach {
                            }
                            try {
                                val data = TravelDatabase.getDatabase(this@MainActivity)
                                data.roomDao().insertTravel(it)
                                data.roomDao().getTravel()
                                Log.e(TAG, "got travel data", )
                            }catch (e:Exception){
                                Log.e(TAG, "onResponse: exception message ${e.message}", )
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

    private fun setCurrentFragment(fragment: Fragment) {

        this.supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }


}