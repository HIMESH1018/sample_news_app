package com.himesh.newsapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ActivityNewsDetailsBinding
import com.himesh.newsapp.models.ArticleDetails
import com.himesh.newsapp.utill.NewsAppConstants

class NewsDetailsActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var mNewsDetailsBinding: ActivityNewsDetailsBinding
    private var temArticleDetails:ArticleDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNewsDetailsBinding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(mNewsDetailsBinding.root)
        setUpObservers()
        btnClicks()
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    private fun btnClicks(){

        mNewsDetailsBinding.btnImgBack.setOnClickListener(this)
        mNewsDetailsBinding.btnImgShare.setOnClickListener(this)
    }

    private fun setUpObservers(){

        temArticleDetails = intent.getSerializableExtra(NewsAppConstants.INTENT_PASS_KEY) as ArticleDetails?
        if(temArticleDetails != null){

            setupUI()
        }
    }

    private fun setupUI(){

        Glide
            .with(this)
            .load(temArticleDetails!!.urlToImage)
            .fitCenter()
            .placeholder(R.drawable.ic_loading)
            .into(mNewsDetailsBinding.imgDetailItem)

        mNewsDetailsBinding.textItemNewsDetailsTime.text = temArticleDetails!!.getTime()
        mNewsDetailsBinding.textItemNewsDetailsDate.text = temArticleDetails!!.date
        mNewsDetailsBinding.textItemNewsDetailsAuthor.text = temArticleDetails!!.author
        mNewsDetailsBinding.textItemNewsDetailsDescription.text = temArticleDetails!!.description
        mNewsDetailsBinding.textItemNewsDetailsTitle.text = temArticleDetails!!.title

        uiVisibility()


    }

    override fun onClick(p0: View?) {
        when(p0){

            mNewsDetailsBinding.btnImgBack ->{

                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }

            mNewsDetailsBinding.btnImgShare -> {

                shareUrl()
            }
        }
    }

    private fun shareUrl(){

        val tempSubBody = "Article URL:${temArticleDetails!!.url}"

        val sIntent = Intent(Intent.ACTION_SEND)
        sIntent.type = NewsAppConstants.TEXT_INTENT

        sIntent.putExtra(Intent.EXTRA_TEXT,tempSubBody)

        startActivity(sIntent)
    }

    private fun uiVisibility(){

        mNewsDetailsBinding.textItemNewsDetailsAuthor.visibility = View.VISIBLE
        mNewsDetailsBinding.textItemNewsDetailsDate.visibility = View.VISIBLE
        mNewsDetailsBinding.textItemNewsDetailsTime.visibility = View.VISIBLE
        mNewsDetailsBinding.textItemNewsDetailsTitle.visibility = View.VISIBLE
        mNewsDetailsBinding.textItemNewsDetailsDescription.visibility = View.VISIBLE
        mNewsDetailsBinding.btnImgShare.visibility = View.VISIBLE
    }
}