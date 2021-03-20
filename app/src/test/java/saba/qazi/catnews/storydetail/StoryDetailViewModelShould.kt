package saba.qazi.catnews.storydetail

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import saba.qazi.catnews.newslist.NewsListViewModel
import saba.qazi.catnews.newslist.data.News
import saba.qazi.catnews.storydetail.data.StoryDetail
import saba.qazi.catnews.utils.BaseUntTest
import saba.qazi.catnews.utils.getValueForTest
import java.lang.RuntimeException

class StoryDetailViewModelShould : BaseUntTest() {

    private lateinit var viewModel: StoryDetailViewModel
    private val service : StoryDetailService = mock()
    private val storyDetail : StoryDetail = mock()
    val id : String = "1"
    private val exception = RuntimeException("Something went wrong.")
    private val expected = Result.success(storyDetail)

    @Test
    fun getStoryDetailFromService() = runBlockingTest{

        mockSuccessfulCase()
        viewModel.storyDetails.getValueForTest()
        verify(service, times(1)).fetchStoryDetail(id)
    }

    @Test
    fun emitStoryDetailFromService()= runBlockingTest{
        mockSuccessfulCase()
        assertEquals(expected, viewModel.storyDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
    mockErrorCase()
        Assert.assertEquals(exception, viewModel.storyDetails.getValueForTest()!!.exceptionOrNull())
    }


    private fun mockSuccessfulCase()  {
        runBlocking {
            whenever(service.fetchStoryDetail(id)).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        viewModel = StoryDetailViewModel(service)
        viewModel.getStoryDetails(id)
    }

    private fun mockErrorCase(){
        runBlocking {
            whenever(service.fetchStoryDetail(id)).thenReturn(
                flow {
                    emit(Result.failure<StoryDetail>(exception))
                }
            )
        }
        viewModel = StoryDetailViewModel(service)
        viewModel.getStoryDetails(id)
    }
}