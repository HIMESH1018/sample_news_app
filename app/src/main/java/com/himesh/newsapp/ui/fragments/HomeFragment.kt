package com.himesh.newsapp.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_mvvm.viewModels.factories.OfflineArticlesViewModelFactory
import com.himesh.newsapp.databinding.FragmentHomeBinding
import com.himesh.newsapp.db.ArticleRepository
import com.himesh.newsapp.db.ArticlesDAO
import com.himesh.newsapp.db.OfflineArticles
import com.himesh.newsapp.db.OfflineArticlesDatabase
import com.himesh.newsapp.models.Article
import com.himesh.newsapp.ui.adapters.NewsAdapter
import com.himesh.newsapp.ui.adapters.OfflineNewsAdapter
import com.himesh.newsapp.ui.viewmodels.HomeViewModel
import com.himesh.newsapp.ui.viewmodels.OfflineArticlesViewModel
import com.himesh.newsapp.utill.NetworkConnectivity
import com.himesh.newsapp.utill.NewsAppConstants
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var mNewsAdapter: NewsAdapter
    private lateinit var mOfflineNewsAdapter: OfflineNewsAdapter

    private lateinit var offlineArticlesViewModel: OfflineArticlesViewModel
    private lateinit var dao:ArticlesDAO
    private lateinit var repository: ArticleRepository
    private lateinit var factory: ViewModelProvider.Factory

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
        initializeDB()
        setUpRecycler()
        setUpObservers()

        if(NetworkConnectivity.isConnected(requireActivity())){
            homeViewModel.getNewsData()
            Log.d(NewsAppConstants.LOG_NET_CHECK,"Connected to Internet")

        }else{
            dao.getAllArticles()
            Log.d(NewsAppConstants.LOG_NET_CHECK,"No Connection")
        }



    }

    private fun initializeDB(){

        dao = OfflineArticlesDatabase.getInstance(requireContext()).articlesDAO
        repository = ArticleRepository(dao)
        factory = OfflineArticlesViewModelFactory(repository)
        offlineArticlesViewModel = ViewModelProvider(this,factory).get(OfflineArticlesViewModel::class.java)

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

                saveArticlesOffline(it)
                Log.d(NewsAppConstants.LOG_DATA_CHECK,"All Articles: $it")
                val toast = Toast.makeText(requireContext(), "Fetching data from Online", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                binding.rlNews.visibility = View.GONE
                Log.d(NewsAppConstants.LOG_DATA_CHECK,"All Articles: No Data")
            }


        }

        offlineArticlesViewModel.offlineArticles.observe(viewLifecycleOwner){

             if (it != null) {
                 binding.rlNews.visibility = View.VISIBLE
                 binding.progressNewsData.visibility = View.GONE
                 mOfflineNewsAdapter.setItems(it)
                 val toast = Toast.makeText(requireContext(), "Fetching data from offline", Toast.LENGTH_SHORT)
                 toast.show()
             }
        }


    }

    private fun saveArticlesOffline(it: ArrayList<Article>) {

        var tempObject: Article? = null

        //before insert deleteAll
        offlineArticlesViewModel.deleteAll()

        for (i in 0 until it.size){

            tempObject = it[i]

            offlineArticlesViewModel.insert(
                OfflineArticles(
                    i+1,
                    tempObject.title,
                    tempObject.author,
                    tempObject.url,
                    tempObject.urlToImage,
                    tempObject.publishedAt,
                    tempObject.content
                )
            )
        }
    }

    private fun setUpRecycler(){

        //Online
        mNewsAdapter = NewsAdapter()
        var articlesList = binding.rlNews
        articlesList.layoutManager = LinearLayoutManager(context)
        articlesList.adapter = mNewsAdapter

        //Offline
        mOfflineNewsAdapter = OfflineNewsAdapter(articleDelete = {

            offlineArticlesViewModel.deleteSingleArticle(it)
            val toast = Toast.makeText(requireContext(), "Deleted article ID: $id", Toast.LENGTH_SHORT)
            toast.show()
            Log.d(NewsAppConstants.LOG_DATA_CHECK, "ClickID: $it")
        })

        var offlineArticlesList = binding.rlNews
        offlineArticlesList.layoutManager = LinearLayoutManager(context)
        offlineArticlesList.adapter = mOfflineNewsAdapter

    }


}