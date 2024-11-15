package com.example.simpleapp.model.api

import com.example.simpleapp.model.entity.CatResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("v1/cats")
    fun fetchCatListFromTMDB(@Query("name") query: String): Call<List<CatResponse>>
}