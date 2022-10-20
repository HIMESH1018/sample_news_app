package com.himesh.newsapp.ui.adapters.viewholders

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ItemNewsHeadBinding
import com.himesh.newsapp.db.OfflineArticles
import com.himesh.newsapp.models.Article
import com.himesh.newsapp.utill.NewsAppConstants

class OfflineNewsDataViewholder(
    var itemViewBinding: ItemNewsHeadBinding,
    private var context: Context
): RecyclerView.ViewHolder(itemViewBinding.root) {



    fun bindData(
        article: OfflineArticles,
        articleDelete: (Int) -> Unit,
        articleDetailsView: (OfflineArticles) -> Unit
    ) {

        Glide
            .with(context)
            .load(article.image)
            .fitCenter()
            .placeholder(R.drawable.ic_loading)
            .into(itemViewBinding.imgItemNews)

        itemViewBinding.textItemNewsTitle.text = article.title
        var publishDate = article.date!!.split("T").toTypedArray()
        var publishTime = publishDate[1].split(":").toTypedArray()


        itemViewBinding.textItemNewsTime.text = (publishTime[0]+" hours ago")
        itemViewBinding.imgItemDelete.visibility = View.VISIBLE


        itemViewBinding.imgItemDelete.setOnClickListener{
            articleDelete(article.id)
        }

        itemViewBinding.cardViewNews.setOnClickListener{
            articleDetailsView(article)
        }



    }
}

