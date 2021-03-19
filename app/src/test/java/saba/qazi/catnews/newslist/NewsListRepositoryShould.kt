package saba.qazi.catnews.newslist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import saba.qazi.catnews.newslist.data.News
import saba.qazi.catnews.newslist.data.NewsRaw
import saba.qazi.catnews.utils.BaseUntTest
import java.lang.RuntimeException

class NewsListRepositoryShould : BaseUntTest() {

    private val exception: Throwable = RuntimeException("something went wrong")
    private val service : NewsListService = mock()
    private val newsListRaw : List<NewsRaw> = mock<List<NewsRaw>>()
    private val newsList : List<News> = mock<List<News>>()
    private val mapper : NewsListMapper = mock()

    @Test
    fun getNewsListsFromService() = runBlockingTest {
        val repository : NewsListRepository = mockSuccessfulCase()
        repository.getNewsList()
        verify(service, times(1)).fetchNewsList()

    }

    @Test
    fun emitMappedNewsListsFromService()= runBlockingTest{
        val repository = mockSuccessfulCase()
        assertEquals(newsList, repository.getNewsList().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getNewsList().first().exceptionOrNull())
    }

    @Test
    fun deligateBusinessLogicToMapper() = runBlockingTest {
        val repository = mockSuccessfulCase()
        repository.getNewsList().first() //first emission
        verify(mapper, times(1)).invoke(newsListRaw)
    }
    private suspend fun mockFailureCase() : NewsListRepository{
        whenever(service.fetchNewsList()).thenReturn(
                flow {
                    emit(Result.failure<List<NewsRaw>>(exception))
                }
        )
        return NewsListRepository(service, mapper)
    }

    private suspend fun mockSuccessfulCase() : NewsListRepository{
        whenever(service.fetchNewsList()).thenReturn(
                flow {
                    emit(Result.success(newsListRaw))
                }
        )

        whenever(mapper.invoke(newsListRaw)).thenReturn(newsList)

        return NewsListRepository(service, mapper)
    }
}