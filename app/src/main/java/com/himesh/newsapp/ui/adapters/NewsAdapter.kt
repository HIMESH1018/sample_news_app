package com.himesh.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ItemNewsHeadBinding
import com.himesh.newsapp.models.Article
import com.himesh.newsapp.ui.adapters.viewholders.NewsDataViewholder

class NewsAdapter():
    RecyclerView.Adapter<NewsDataViewholder>() {


    private var mNewsArticles: ArrayList<Article>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): NewsDataViewholder {
        val dataBinding: ItemNewsHeadBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news_head, parent, false
        )

        return NewsDataViewholder(dataBinding,parent.context)
    }

    override fun onBindViewHolder(holder: NewsDataViewholder, position: Int) {
        holder.bindData(mNewsArticles!![position])
    }

    override fun getItemCount(): Int {
        return if (mNewsArticles.isNullOrEmpty()) {
            0
        } else {
            mNewsArticles!!.size
        }
    }


    fun setItems(articles: ArrayList<Article>) {
        mNewsArticles = articles
        notifyDataSetChanged()
    }

}