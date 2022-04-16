package com.rchyn.sqlbasics.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rchyn.sqlbasics.data.entity.CaliforniaPark

@Dao
interface CaliforniaParkDao {

    @Insert
    suspend fun insertAll(parks: List<CaliforniaPark>)

    @Query("SELECT * FROM park")
    suspend fun getAll(): List<CaliforniaPark>
}