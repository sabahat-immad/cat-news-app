package saba.qazi.catnews.storydetail

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import saba.qazi.catnews.storydetail.data.StoryDetail
import javax.inject.Inject

class StoryDetailService @Inject constructor(
    private val storyDetailAPI: StoryDetailAPI
){

    suspend fun fetchStoryDetail(id: String) : Flow<Result<StoryDetail>> {
        return flow{

            val storyDetail : StoryDetail = storyDetailAPI.fetchStoryDetail(id)
            emit(Result.success(storyDetail))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }


}
