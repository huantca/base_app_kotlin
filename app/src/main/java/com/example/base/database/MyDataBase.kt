package com.example.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.base.database.dao.MyDao
import com.example.base.database.model.MySample

@Database(entities = [MySample::class], version = 1,exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun getMyDao(): MyDao
}