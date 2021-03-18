package saba.qazi.catnews.newslist

import kotlinx.coroutines.flow.Flow
import saba.qazi.catnews.newslist.data.News
import javax.inject.Inject

class NewsListRepository @Inject constructor(
        private val service: NewsListService
) {
    suspend fun getNewsList() : Flow<Result<List<News>>> =
            service.fetchNewsList()


}
