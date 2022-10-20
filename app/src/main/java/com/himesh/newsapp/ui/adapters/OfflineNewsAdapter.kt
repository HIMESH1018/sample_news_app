package com.himesh.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ItemNewsHeadBinding
import com.himesh.newsapp.db.OfflineArticles
import com.himesh.newsapp.ui.adapters.viewholders.OfflineNewsDataViewholder

class OfflineNewsAdapter(
    private val articleDelete: (Int) -> Unit,
    private val articleDetailsView:(OfflineArticles) ->Unit
):
    RecyclerView.Adapter<OfflineNewsDataViewholder>() {


    private var mOfflineNewsArticles: List<OfflineArticles>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): OfflineNewsDataViewholder {
        val dataBinding: ItemNewsHeadBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news_head, parent, false
        )

        return OfflineNewsDataViewholder(dataBinding,parent.context)
    }

    override fun onBindViewHolder(holder: OfflineNewsDataViewholder, position: Int) {
        holder.bindData(mOfflineNewsArticles!![position],articleDelete,articleDetailsView)
    }

    override fun getItemCount(): Int {
        return if (mOfflineNewsArticles.isNullOrEmpty()) {
            0
        } else {
            mOfflineNewsArticles!!.size
        }
    }

    fun setItems(articles: List<OfflineArticles>) {
        mOfflineNewsArticles = articles
        notifyDataSetChanged()
    }

}