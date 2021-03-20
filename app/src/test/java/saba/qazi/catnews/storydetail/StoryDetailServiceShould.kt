package saba.qazi.catnews.storydetail

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import saba.qazi.catnews.storydetail.data.StoryDetail


class StoryDetailServiceShould {

    private lateinit var service : StoryDetailService
    private val storyDetailApi : StoryDetailAPI = mock()
    private val storyDetail : StoryDetail = mock()
    private val id = "1"
    private val exception = RuntimeException("Damn backend developers again 500!!!")

    @Test
    fun fetchStoryDetailFromApi()= runBlockingTest{
       mockSuccessfulCase()
       service.fetchStoryDetail(id).single()
        verify(storyDetailApi, times(1)).fetchStoryDetail(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(Result.success(storyDetail), service.fetchStoryDetail(id).single())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest {
        mockErrorCase()

        TestCase.assertEquals(
            "Something went wrong",
            service.fetchStoryDetail(id).single().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(storyDetailApi.fetchStoryDetail(id)).thenThrow(exception)

        service = StoryDetailService(storyDetailApi)
    }


    private suspend fun mockSuccessfulCase() {
        whenever(storyDetailApi.fetchStoryDetail(id)).thenReturn(storyDetail)

        service = StoryDetailService(storyDetailApi)
    }
}