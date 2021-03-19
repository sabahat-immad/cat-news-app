package saba.qazi.catnews.newslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_newslist.*
import kotlinx.android.synthetic.main.fragment_newslist.view.*
import saba.qazi.catnews.R
import saba.qazi.catnews.newslist.data.News
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    lateinit var viewModel : NewsListViewModel

    @Inject
    lateinit var viewModelFactory : NewsListViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_newslist, container, false)

        setupViewModel()

        viewModel.loader.observe(this as LifecycleOwner, {loading ->
            when(loading){
                true -> loader.visibility = View.VISIBLE
                else -> loader.visibility = View.GONE
            }
        })
        viewModel.newsList.observe(this as LifecycleOwner, { newsList ->
            if (newsList.getOrNull() != null)
            setupList(view.news_list, newsList.getOrNull()!!)
            else{
            //TODO
            }
        })

        return view
    }

    private fun setupList(
        view: View?,
        newsList : List<News>
    ) {
        with (view as RecyclerView) {

                layoutManager = LinearLayoutManager(context)
                adapter = NewsListRecyclerViewAdapter(newsList) { id ->
                    val action =
                        NewsListFragmentDirections.actionNewsListFragmentToStoryDetailFragment(id)
                    findNavController().navigate(action)
                }

        }
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NewsListFragment().apply {

            }
    }
}