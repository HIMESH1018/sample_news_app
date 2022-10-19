package com.himesh.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.himesh.newsapp.databinding.FragmentNewsDetailsBinding




class NewsDetailsFragment : Fragment() {

    private var mNewsDetailsFragment: FragmentNewsDetailsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mNewsDetailsFragment =
            FragmentNewsDetailsBinding.inflate(inflater, container, false)

        return mNewsDetailsFragment!!.root
    }


}