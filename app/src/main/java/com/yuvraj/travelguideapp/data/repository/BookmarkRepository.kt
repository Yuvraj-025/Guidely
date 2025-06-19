package com.yuvraj.travelguideapp.data.repository

import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.room.RoomDao

class BookmarkRepository(private val roomDao: RoomDao){
    val getTravel: List<TravelModel> = roomDao.getTravel()
}