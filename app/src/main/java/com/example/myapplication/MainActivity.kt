package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.AuthorAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.AuthorModel
import com.example.myapplication.viewmodel.AuthorViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    var layoutBinding: ActivityMainBinding? = null
    var viewModel: AuthorViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        layoutBinding?.apply {
            lifecycleOwner = this@MainActivity
            viewModel = ViewModelProvider(this@MainActivity).get(AuthorViewModel::class.java)
        }
        viewModel?.apiCalling()
        viewModelOperation()
    }


// getting data from viewmodel
    fun viewModelOperation() {
        viewModel?.apply {
            authorDetailsLiveData.observe(this@MainActivity, Observer {
                it?.let {
                    setAdapter(it)
                }
            })

            somethinwentLiveData.observe(this@MainActivity, Observer {
                it?.let {
                    if (it){
                        layoutBinding.apply {
                            this?.rvAssigment?.visibility = View.GONE
                            this?.ivsomeThingwentWrong?.visibility = View.VISIBLE
                            this?.tvsomeThingwentWong?.visibility = View.VISIBLE
                        }
                    }else{
                        layoutBinding.apply {
                        this?.rvAssigment?.visibility = View.VISIBLE
                        this?.ivsomeThingwentWrong?.visibility = View.GONE
                        this?.tvsomeThingwentWong?.visibility = View.GONE
                        }
                    }
                }
            })
        }

    }

    // setup of recyclerview
    fun setAdapter(dataList:ArrayList<AuthorModel>){
        val layoutManager = GridLayoutManager(this@MainActivity, 2, GridLayoutManager.VERTICAL, false)
        val adapter = viewModel?.let { AuthorAdapter(this@MainActivity, dataList, it) }
        layoutBinding?.apply {
            this.rvAssigment.layoutManager = layoutManager
            this.rvAssigment.adapter = adapter
        }

    }


}



