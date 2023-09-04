package com.example.stillfilms.features.splash.data.entity

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFilm(film: FilmEntity)

    @Update
    fun updateFilm(film: FilmEntity)

    @Query("SELECT * FROM films_table ORDER BY id ASC")
    fun readData(): LiveData<List<FilmEntity>>

    @Delete
    suspend fun deleteFilm(film: FilmEntity)

    @Query("DELETE FROM films_table")
    fun clear()
}