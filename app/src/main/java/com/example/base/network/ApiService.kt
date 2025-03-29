package com.example.base.network

import com.example.base.database.dao.MyDao
import com.example.base.database.model.MySample
import retrofit2.http.GET
import javax.inject.Inject

interface ApiService {
    @GET("sample")
    suspend fun getAllPost():List<MySample>
}