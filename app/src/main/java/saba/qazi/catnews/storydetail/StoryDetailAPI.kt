package saba.qazi.catnews.storydetail

import retrofit2.http.GET
import retrofit2.http.Path
import saba.qazi.catnews.storydetail.data.StoryDetail

interface StoryDetailAPI {

    @GET("/story/{id}")
    suspend fun fetchStoryDetail(@Path("id")id: String) : StoryDetail

}
