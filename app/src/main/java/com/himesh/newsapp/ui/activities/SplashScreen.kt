package com.himesh.newsapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ActivitySplashScreenBinding


class SplashScreen : AppCompatActivity() {

    private lateinit var mSplashBinding: ActivitySplashScreenBinding
    private var top: Animation? = null
    private val splashTimeout = 4500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSplashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(mSplashBinding.root)
        setUpUI()
    }


    private fun setUpUI(){

        top = AnimationUtils.loadAnimation(this, R.anim.top_animation)

        mSplashBinding.imgSplashLogo.animation = top

        nextScreen()
    }

    private fun nextScreen(){

        Handler().postDelayed(Runnable { //  bestSellers();
            val HomeIntent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(HomeIntent)
            finish()
        }, splashTimeout.toLong())
    }
}