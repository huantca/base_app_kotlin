package com.example.base.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_sample")
data class MySample(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    val name: String,
    val age: Int
) {

}