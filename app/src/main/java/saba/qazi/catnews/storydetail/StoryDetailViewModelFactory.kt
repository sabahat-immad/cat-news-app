package saba.qazi.catnews.storydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import saba.qazi.catnews.newslist.NewsListRepository
import saba.qazi.catnews.newslist.NewsListViewModel
import javax.inject.Inject

class StoryDetailViewModelFactory @Inject constructor(
    private val service: StoryDetailService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoryDetailViewModel(service) as T
    }

}

