package com.himesh.newsapp.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.himesh.newsapp.databinding.FragmentHomeBinding
import com.himesh.newsapp.ui.adapters.NewsAdapter
import com.himesh.newsapp.ui.viewmodels.HomeViewModel
import com.himesh.newsapp.utill.NetworkConnectivity
import com.himesh.newsapp.utill.NewsAppConstants

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var mNewsAdapter: NewsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpUI()

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUI(){

        if(NetworkConnectivity.isConnected(requireActivity())){
            homeViewModel.getNewsData()
            Log.d(NewsAppConstants.LOG_NET_CHECK,"Connected to Internet")
        }else{

            Log.d(NewsAppConstants.LOG_NET_CHECK,"No Connection")
        }


        setUpRecycler()
        setUpObservers()
    }

    private fun setUpObservers(){

        homeViewModel.mProgress.observe(viewLifecycleOwner) {

            if(it){
                binding.progressNewsData.visibility = View.VISIBLE
            }else{

                binding.progressNewsData.visibility = View.GONE
            }


        }

        homeViewModel.mArticles.observe(viewLifecycleOwner){

            if(it != null) {
                mNewsAdapter.setItems(it)
                binding.rlNews.visibility = View.VISIBLE
                Log.d(NewsAppConstants.LOG_DATA_CHECK,"All Articles: $it")
            }else{
                binding.rlNews.visibility = View.GONE
                Log.d(NewsAppConstants.LOG_DATA_CHECK,"All Articles: No Data")
            }


        }


    }

    private fun setUpRecycler(){

        mNewsAdapter = NewsAdapter()
        var articlesList = binding.rlNews
        articlesList.layoutManager = LinearLayoutManager(context)
        articlesList.adapter = mNewsAdapter

    }
}