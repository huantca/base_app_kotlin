package com.example.base.di

import android.content.Context
import androidx.room.Room
import com.example.base.database.MyDataBase
import com.example.base.database.dao.MyDao
import com.example.base.network.ApiService
import com.example.base.network.ApiServiceImp
import com.example.base.repository.MyRepository
import com.example.base.repository.test1
import com.example.base.repository.testInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): MyDataBase {
        return Room.databaseBuilder(context, MyDataBase::class.java, "my_db").build()
    }

    @Provides
    fun providesMyDao(myDataBase: MyDataBase) = myDataBase.getMyDao()


    @Provides
    fun providesRepository(myDao: MyDao, apiServiceImp: ApiServiceImp,testInterface: testInterface) =
        MyRepository(myDao, apiServiceImp,testInterface)

    @Provides
    fun providesTestInterface(): testInterface = test1()


    @Provides
    fun providesBaseUrl(): String = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providesRetrofitBuilder(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun providesApiServiceImp(apiService: ApiService): ApiServiceImp = ApiServiceImp(apiService)
}