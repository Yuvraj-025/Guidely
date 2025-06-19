package com.yuvraj.travelguideapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yuvraj.travelguideapp.domain.model.TravelModel


class Converters {

    @TypeConverter
    fun listToJsonString(value: List<TravelModel.ImageRoomlist>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<TravelModel.ImageRoomlist>::class.java).toList()
}