package com.himesh.newsapp.ui.adapters.viewholders

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ItemNewsHeadBinding
import com.himesh.newsapp.models.Article
import com.himesh.newsapp.utill.NewsAppConstants

class NewsDataViewholder(
    var itemViewBinding: ItemNewsHeadBinding,
    private var context: Context
): RecyclerView.ViewHolder(itemViewBinding.root) {



    fun bindData(article: Article) {

        Glide
            .with(context)
            .load(article.urlToImage)
            .fitCenter()
            .placeholder(R.drawable.ic_loading)
            .into(itemViewBinding.imgItemNews)

        itemViewBinding.textItemNewsTitle.text = article.title
        var publishDate = article.publishedAt.split("T").toTypedArray()
        var publishTime = publishDate[1].split(":").toTypedArray()


        itemViewBinding.textItemNewsTime.text = (publishTime[0]+" hours ago")

    }
}