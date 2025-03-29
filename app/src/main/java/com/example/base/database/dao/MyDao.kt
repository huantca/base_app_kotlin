package com.example.base.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.base.database.model.MySample
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myDB: MySample)

    @Query("SELECT * FROM my_sample")
    fun getAll(): Flow<List<MySample>>

    @Query("DELETE FROM my_sample")
    suspend fun deleteAll()


}