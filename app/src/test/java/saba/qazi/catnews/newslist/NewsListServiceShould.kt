package saba.qazi.catnews.newslist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import saba.qazi.catnews.newslist.data.CatNewsHeadlines
import saba.qazi.catnews.newslist.data.News
import saba.qazi.catnews.utils.BaseUntTest
import java.lang.RuntimeException

class NewsListServiceShould : BaseUntTest() {

    private lateinit var service : NewsListService
    private val newsListApi : NewsListAPI = mock()
    private val newsList : CatNewsHeadlines = mock()


    @Test
    fun fetchNewsListFromApi() = runBlockingTest{
        service = NewsListService(newsListApi)
        service.fetchNewsList().first()
        verify(newsListApi,times(1)).fetchCatNews()
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
    mockSuccessfulCase()
        assertEquals(Result.success(newsList.newsData), service.fetchNewsList().first())
    }


    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockFailureCase()
        assertEquals("Something went wrong",
            service.fetchNewsList().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(newsListApi.fetchCatNews())
            .thenThrow(RuntimeException("Damn backend developers"))

        service = NewsListService(newsListApi)
    }


    private suspend fun mockSuccessfulCase() {
        whenever(newsListApi.fetchCatNews()).thenReturn(newsList)
        service = NewsListService(newsListApi)
    }
}