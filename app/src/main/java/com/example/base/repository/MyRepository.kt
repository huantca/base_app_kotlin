package com.example.base.repository

import com.example.base.database.dao.MyDao
import com.example.base.database.model.MySample
import com.example.base.network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MyRepository @Inject constructor(
    private val dao: MyDao,
    private val apiServiceImp: ApiServiceImp,
    private val testInterface: testInterface
) {
    fun getAll(): Flow<List<MySample>> {
        testInterface.getData()
        testInterface.clickItem(10)
        return dao.getAll()
    }

    suspend fun insert(postList: MySample) = withContext(Dispatchers.IO) {
        dao.insert(postList)
    }
}