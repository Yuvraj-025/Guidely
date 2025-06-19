package com.yuvraj.travelguideapp.domain.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yuvraj.travelguideapp.data.repository.BookmarkRepository
import com.yuvraj.travelguideapp.room.TravelDatabase

class TravelViewModel (application: Application): AndroidViewModel(application){
    private val repository:BookmarkRepository

    init {
        val roomDao = TravelDatabase.getDatabase(application).roomDao()
        repository = BookmarkRepository(roomDao)
    }

}