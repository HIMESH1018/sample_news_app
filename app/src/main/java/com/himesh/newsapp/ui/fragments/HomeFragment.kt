package com.himesh.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_mvvm.viewModels.factories.OfflineArticlesViewModelFactory
import com.himesh.newsapp.R
import com.himesh.newsapp.databinding.FragmentHomeBinding
import com.himesh.newsapp.db.ArticleRepository
import com.himesh.newsapp.db.ArticlesDAO
import com.himesh.newsapp.db.OfflineArticles
import com.himesh.newsapp.db.OfflineArticlesDatabase
import com.himesh.newsapp.models.Article
import com.himesh.newsapp.models.ArticleDetails
import com.himesh.newsapp.ui.adapters.NewsAdapter
import com.himesh.newsapp.ui.adapters.OfflineNewsAdapter
import com.himesh.newsapp.ui.viewmodels.ArticleDetailsViewModel
import com.himesh.newsapp.ui.viewmodels.HomeViewModel
import com.himesh.newsapp.ui.viewmodels.OfflineArticlesViewModel
import com.himesh.newsapp.utill.NetworkConnectivity
import com.himesh.newsapp.utill.NewsAppConstants

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels ()
    private val articleViewmodel:ArticleDetailsViewModel by activityViewModels ()
    private lateinit var mNewsAdapter: NewsAdapter
    private lateinit var mOfflineNewsAdapter: OfflineNewsAdapter

    private lateinit var offlineArticlesViewModel: OfflineArticlesViewModel
    private lateinit var dao:ArticlesDAO
    private lateinit var repository: ArticleRepository
    private lateinit var factory: ViewModelProvider.Factory
    private var tempNetwork:Boolean? = null
    private var mBundle = Bundle()

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

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUI(){
        checkNetwork()
        initializeDB()
        setUpObservers()



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
                binding.rlNews.visibility = View.VISIBLE
                mNewsAdapter.setItems(it)
                Log.e("Check:","checkkk"+it.size)
                saveArticlesOffline(it)
                val toast = Toast.makeText(requireContext(), "Fetching data from Online", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                binding.rlNews.visibility = View.GONE
                Log.d(NewsAppConstants.LOG_DATA_CHECK,"All Articles: No Data")
            }


        }

        offlineArticlesViewModel.offlineArticles.observe(viewLifecycleOwner){

            if(!tempNetwork!!) {
                if (it != null) {
                    binding.rlNews.visibility = View.VISIBLE
                    binding.progressNewsData.visibility = View.GONE
                    mOfflineNewsAdapter.setItems(it)
                    val toast = Toast.makeText(
                        requireContext(),
                        "Fetching data from offline",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
        }

        homeViewModel.mNetworkConnected.observe(viewLifecycleOwner){
            tempNetwork= it
            Log.e("Connections","status"+tempNetwork)
            if(it){
                setUpOnlineRecycler()
                homeViewModel.getNewsData()
            }else{
                 setUpOfflineRecycler()
                 dao.getAllArticles()
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
                    tempObject.content,
                    tempObject.description
                )
            )
        }
    }

    private fun setUpOnlineRecycler(){

        //Online
        mNewsAdapter = NewsAdapter(articleDetailsView = {
            viewFullArticleFromOnline(it)
        })
        var articlesList = binding.rlNews
        articlesList.layoutManager = LinearLayoutManager(context)
        articlesList.adapter = mNewsAdapter



    }


    private fun setUpOfflineRecycler(){

        //Offline
        mOfflineNewsAdapter = OfflineNewsAdapter(
            //Delete Article
            articleDelete = {
                offlineArticlesViewModel.deleteSingleArticle(it)
                val toast = Toast.makeText(requireContext(), "Deleted article ID: $id", Toast.LENGTH_SHORT)
                toast.show()
                Log.d(NewsAppConstants.LOG_DATA_CHECK, "ClickID: $it")
            },
            //View the full article
            articleDetailsView = {
                viewFullArticleFromOffline(it)
            }
        )

        var offlineArticlesList = binding.rlNews
        offlineArticlesList.layoutManager = LinearLayoutManager(context)
        offlineArticlesList.adapter = mOfflineNewsAdapter
    }

    private fun viewFullArticleFromOnline(article: Article) {

         var articleDetails:ArticleDetails? = null

         articleDetails = ArticleDetails(
             article.author,
             article.content,
             article.description,
             article.publishedAt,
             article.title,
             article.url,
             article.urlToImage
         )

        Log.e("CheckFEtch",""+articleDetails)
        articleViewmodel.saveSingleArticles(articleDetails)
        fragmentTransfer(articleDetails)
    }

    private fun viewFullArticleFromOffline(article: OfflineArticles) {

        var articleDetails:ArticleDetails? = null

        articleDetails = ArticleDetails(
            article.author.orEmpty(),
            article.content!!,
            article.description!!,
            article.date!!,
            article.title!!,
            article.url!!,
            article.image!!
        )
        Log.e("CheckFEtch",""+articleDetails)
        articleViewmodel.saveSingleArticles(articleDetails)
        fragmentTransfer(articleDetails)

    }

    private fun fragmentTransfer(articleDetails: ArticleDetails) {
        mBundle.putSerializable(NewsAppConstants.INTENT_PASS_KEY, articleDetails)
        requireView().findNavController().navigate(R.id.action_nav_home_to_newsDetailsActivity,mBundle)

    }

    private fun checkNetwork(){

        if(NetworkConnectivity.isConnected(requireContext())){
            homeViewModel.mNetworkConnected.value = true
            Log.d(NewsAppConstants.LOG_NET_CHECK,"Connected to Internet")

        }else{
            homeViewModel.mNetworkConnected.value = false
            Log.d(NewsAppConstants.LOG_NET_CHECK,"No Connection")
        }
    }


}


