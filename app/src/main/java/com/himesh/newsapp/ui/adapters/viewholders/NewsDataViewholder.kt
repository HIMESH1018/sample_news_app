package com.himesh.newsapp.ui.adapters.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ItemNewsHeadBinding

class NewsDataViewholder(
    var itemViewBinding: ItemNewsHeadBinding,
    private var context: Context
): RecyclerView.ViewHolder(itemViewBinding.root) {



    fun bindData(){

        Glide
            .with(context)
            .load(itemViewBinding.mNews!!.urlToImage)
            .centerCrop()
            .placeholder(R.drawable.ic_loading)
            .into(itemViewBinding.imgItemNews)


    }
}