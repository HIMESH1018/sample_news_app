package com.himesh.newsapp.ui.adapters.viewholders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ItemNewsHeadBinding
import com.himesh.newsapp.models.Article


class NewsDataViewholder(
    var itemViewBinding: ItemNewsHeadBinding,
    private var context: Context
): RecyclerView.ViewHolder(itemViewBinding.root) {



    fun bindData(article: Article, articleDetailsView: (Article) -> Unit) {

        Glide
            .with(context)
            .load(article.urlToImage)
            .fitCenter()
            .placeholder(R.drawable.ic_loading)
            .into(itemViewBinding.imgItemNews)

        itemViewBinding.textItemNewsTitle.text = article.title
        itemViewBinding.imgItemDelete.visibility = View.GONE

        val publishDate = article.publishedAt.split("T").toTypedArray()
        val publishTime = publishDate[1].split(":").toTypedArray()


        itemViewBinding.textItemNewsTime.text = (publishTime[0]+" hours ago")

        itemViewBinding.cardViewNews.setOnClickListener{
            articleDetailsView(article)
        }
    }
}