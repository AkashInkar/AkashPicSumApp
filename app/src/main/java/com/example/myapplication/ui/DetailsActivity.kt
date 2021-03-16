package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    var binding: ActivityDetailsBinding? = null
    var id: Int? = 0
    var name: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        id = intent.getIntExtra("ID",0)
        name = intent.getStringExtra("Name")
        binding?.tvName?.text = name
        Glide.with(this@DetailsActivity).load("https://picsum.photos/300/300?image=${id}").into(binding?.ivProfile!!)
    }

}