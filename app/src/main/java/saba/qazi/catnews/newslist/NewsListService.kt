package saba.qazi.catnews.newslist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import saba.qazi.catnews.newslist.data.News
import saba.qazi.catnews.newslist.data.NewsRaw
import java.lang.RuntimeException
import javax.inject.Inject

class NewsListService @Inject constructor(
        private val newsListAPI: NewsListAPI
)  {

    suspend fun fetchNewsList() : Flow<Result<List<NewsRaw>>> {

         return flow{
             val newsList : List<NewsRaw> = newsListAPI.fetchCatNews().newsData
             emit(Result.success(newsList))
         }.catch {
             emit(Result.failure(RuntimeException("Something went wrong")))
         }
}

}
