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
import saba.qazi.catnews.utils.BaseUntTest
import java.lang.RuntimeException

class NewsListRepositoryShould : BaseUntTest() {

    private val exception: Throwable = RuntimeException("something went wrong")
    private val service : NewsListService = mock()
    private val repository = NewsListRepository(service)
    private val newsRaw : List<NewsRaw> = mock<List<NewsRaw>>()
    private val newsList : List<News> = mock<List<News>>()

    @Test
    fun getNewsListsFromService() = runBlockingTest {
        repository.getNewsList()
        verify(service, times(1)).fetchNewsList()

    }

    @Test
    fun emitNewsListsFromService()= runBlockingTest{
        mockSuccessfulCase()
        assertEquals(newsList, repository.getNewsList().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runBlockingTest {
        mockFailureCase()
        assertEquals(exception, repository.getNewsList().first().exceptionOrNull())
    }

    private suspend fun mockFailureCase() {
        whenever(service.fetchNewsList()).thenReturn(
                flow {
                    emit(Result.failure<List<News>>(exception))
                }
        )
    }

    private suspend fun mockSuccessfulCase(){
        whenever(service.fetchNewsList()).thenReturn(
                flow {
                    emit(Result.success(newsList))
                }
        )

    }
}