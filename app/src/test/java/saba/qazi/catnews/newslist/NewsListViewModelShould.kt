package saba.qazi.catnews.newslist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import saba.qazi.catnews.newslist.data.News
import saba.qazi.catnews.utils.BaseUntTest
import saba.qazi.catnews.utils.captureValues
import saba.qazi.catnews.utils.getValueForTest
import java.lang.RuntimeException


class NewsListViewModelShould : BaseUntTest() {

    private val repository : NewsListRepository = mock()
    private val newsList = mock<List<News>>()
    private val expected = Result.success(newsList)
    private val exception = RuntimeException("Something went wrong.")


    @Test
    fun getNewsListFromRepository() = runBlockingTest{

        val viewModel = mockSuccessfulResult()
        viewModel.newsList.getValueForTest()
        verify(repository, times(1)).getNewsList()
    }

    @Test
    fun emitNewsListsFromRepository() = runBlockingTest{
        val viewModel = mockSuccessfulResult()
        assertEquals(expected, viewModel.newsList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(){
        val viewModel = mockErrorCase()

        assertEquals(exception, viewModel.newsList.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun displayLoaderWhenLoading() = runBlockingTest{
       val viewModel = mockSuccessfulResult()

       viewModel.loader.captureValues{
           viewModel.newsList.getValueForTest()
           assertEquals(true, values[0])
       }
    }

    @Test
    fun hideLoaderWhenLoadingIsFinished() = runBlockingTest{
        val viewModel = mockSuccessfulResult()

        viewModel.loader.captureValues{
            viewModel.newsList.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun hideLoaderWhenError() = runBlockingTest{
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues{
            viewModel.newsList.getValueForTest()
            assertEquals(false, values.last())
        }
    }
    private fun mockSuccessfulResult(): NewsListViewModel {
        runBlocking {
            whenever(repository.getNewsList()).thenReturn(
                    flow {
                        emit(expected)
                    }
            )
        }
        return NewsListViewModel(repository)
    }

    private fun mockErrorCase(): NewsListViewModel {
        runBlocking {
            whenever(repository.getNewsList()).thenReturn(
                    flow {
                        emit(Result.failure<List<News>>(exception))
                    }
            )
        }

        return NewsListViewModel(repository)
    }
}