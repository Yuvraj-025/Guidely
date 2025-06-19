package com.yuvraj.travelguideapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yuvraj.travelguideapp.domain.model.TravelModel
import com.yuvraj.travelguideapp.utils.Converters


@Database(entities = [TravelModel::class],version = 1)
@TypeConverters(Converters::class)
abstract class TravelDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        private var instance: TravelDatabase? = null

        fun getDatabase(context: Context): TravelDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    TravelDatabase::class.java,
                    "travel.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance as TravelDatabase

        }
    }
}