package com.example.myapplication.apiClient

import com.example.myapplication.model.AuthorModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


object ApiClient {

    var BASE_URL = "https://picsum.photos/"
    var apiInterface: APIInterFace? = null


    fun getClient(): APIInterFace? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(APIInterFace::class.java)
        return apiInterface
    }


    interface APIInterFace {
        @GET("list")
        fun getall(): Call<List<AuthorModel>>?

    }
}