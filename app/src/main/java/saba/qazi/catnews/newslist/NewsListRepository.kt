package saba.qazi.catnews.newslist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import saba.qazi.catnews.newslist.data.News
import javax.inject.Inject

class NewsListRepository @Inject constructor(
        private val service: NewsListService,
        private val mapper : NewsListMapper
) {
    suspend fun getNewsList() : Flow<Result<List<News>>> =
            service.fetchNewsList().map {
                if(it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
                else
                    Result.failure(it.exceptionOrNull()!!)
            }


}
