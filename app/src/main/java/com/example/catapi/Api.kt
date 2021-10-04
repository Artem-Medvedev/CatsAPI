package com.example.catapi

import retrofit2.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CatApi {

    @GET("/v1/images/search?limit=10")
     suspend fun getListOfCats(): List<ApiData>
}

object CatApiImpl {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.thecatapi.com")
        .build()

    private val catService = retrofit.create(CatApi::class.java)

    suspend fun getListOfCats(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catService.getListOfCats().map {
                cat -> Cat(cat.url)
            }
        }
    }
}