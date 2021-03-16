package com.example.myapplication.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.apiClient.ApiClient
import com.example.myapplication.model.AuthorModel
import com.example.myapplication.ui.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AuthorViewModel():ViewModel(){
    var somethinwentLiveData = MutableLiveData<Boolean>().apply {
        value = false
    }
    var authorDetailsLiveData = MutableLiveData<ArrayList<AuthorModel>>().apply {
        value = ArrayList<AuthorModel>()
    }


//  get data from server
    fun apiCalling() {
        val call: Call<List<AuthorModel>> =
            ApiClient.getClient()?.getall() as Call<List<AuthorModel>>

        call.enqueue(object : Callback<List<AuthorModel>> {
            override fun onResponse(
                call: Call<List<AuthorModel>>,
                response: Response<List<AuthorModel>>
            ) {
                val authorResponse = response.body()
                if (authorResponse != null) {
                    authorDetailsLiveData.postValue(authorResponse as ArrayList<AuthorModel>?)
                }

            }

            override fun onFailure(call: Call<List<AuthorModel>>, t: Throwable) {
                somethinwentLiveData.postValue(true)
            }
        })
    }

    fun onImageClick(view:View,data: AuthorModel){
        val intent = Intent(view.context, DetailsActivity::class.java)
        intent.putExtra("ID",data.id!!)
        intent.putExtra("Name",data.author)
        view.context.startActivity(intent)
    }

}