package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
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
           // requestWindowFeature(Window.FEATURE_NO_TITLE);
            setSupportActionBar(maintoolbar)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        try {
            val item = menu!!.findItem(R.id.menuCart)
            MenuItemCompat.setActionView(item,R.layout.menu_file_layout)
            val notifCount = MenuItemCompat.getActionView(item) as RelativeLayout

            val tv = notifCount.findViewById<View>(R.id.actionbar_notifcation_textview) as TextView
            tv.text = "99"


        }catch (e:Exception){
            e.printStackTrace()
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.menuCart ->{
                Toast.makeText(this,"Demo for the test",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}



