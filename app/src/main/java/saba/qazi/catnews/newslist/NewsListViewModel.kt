package saba.qazi.catnews.newslist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.onEach
import saba.qazi.catnews.newslist.data.News

class NewsListViewModel(
        private val repository: NewsListRepository
) : ViewModel(){

    val loader = MutableLiveData<Boolean>()
    val newsList = liveData<Result<List<News>>> {
        loader.postValue(true)
        emitSource(repository.getNewsList()
                .onEach {
                    loader.postValue(false)
                }
                .asLiveData())
    }

}
