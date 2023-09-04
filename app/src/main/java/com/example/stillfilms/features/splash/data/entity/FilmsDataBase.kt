package com.example.stillfilms.features.splash.data.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class FilmsDataBase: RoomDatabase() {

    abstract fun filmsDao(): FilmsDao

    companion object{

        private var INSTANCE: FilmsDataBase? = null

        fun getDataBase(context: Context): FilmsDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmsDataBase::class.java,
                    "films_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}