package com.himesh.newsapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.ImageButton
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.ActivityMainBinding
import com.himesh.newsapp.ui.viewmodels.HomeViewModel
import com.himesh.newsapp.utill.NetworkConnectivity
import com.himesh.newsapp.utill.NewsAppConstants

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var drawerLayout: DrawerLayout? = null
    var btnImgNav: ImageButton? = null
    var navView: NavigationView? = null
    var navController: NavController? = null

    val homeViewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavDrawer()

    }


    private fun setUpNavDrawer() {


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        btnImgNav = binding.appBarMain.btnImgNav
        binding.appBarMain.textHomeTitleDate.text = homeViewModel.FROM
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )

        navView!!.setupWithNavController(navController!!)
        btnImgNav!!.setOnClickListener(this)

    }

    private fun setSupportActionBar() {
        drawerLayout!!.openDrawer(Gravity.LEFT)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClick(p0: View?) {

        when (p0) {

            btnImgNav -> {
                setSupportActionBar()
            }
        }

    }





}