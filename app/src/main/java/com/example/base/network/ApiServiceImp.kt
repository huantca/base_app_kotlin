package com.example.base.network

import com.example.base.database.model.MySample
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {

    suspend fun getAll(): List<MySample> = apiService.getAllPost()
}