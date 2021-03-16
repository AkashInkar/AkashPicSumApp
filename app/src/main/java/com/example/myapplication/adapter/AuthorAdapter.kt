package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.CardviewAuthorItemListBinding
import com.example.myapplication.model.AuthorModel
import com.example.myapplication.viewmodel.AuthorViewModel
import java.util.ArrayList

class AuthorAdapter(var mContext: Context, var dataList: ArrayList<AuthorModel>,var authorViewModel: AuthorViewModel):RecyclerView.Adapter<AuthorAdapter.AuthorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_author_item_list,parent,false)
        return AuthorViewHolder(view)
    }
    override fun getItemCount(): Int {
       return dataList.size
    }
    override fun onBindViewHolder(holder: AuthorViewHolder, position: Int) {
        val pojo = dataList[position]
        holder.bind(pojo)
    }

    inner class AuthorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardviewAuthorItemListBinding: CardviewAuthorItemListBinding? = null
        init {
            cardviewAuthorItemListBinding = DataBindingUtil.bind(itemView)
        }
        fun bind(pojo: AuthorModel) {
            cardviewAuthorItemListBinding?.apply {
                this.modelList = pojo
                this.viewModel = authorViewModel
                Glide.with(mContext).load("https://picsum.photos/300/300?image=${pojo.id}").into(this.ivAuthorProfile)
            }
        }
    }
}