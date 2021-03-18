package saba.qazi.catnews.newslist

import retrofit2.http.GET
import saba.qazi.catnews.newslist.data.CatNewsHeadlines

interface NewsListAPI {

    @GET("/news-list")
    suspend fun fetchCatNews() : CatNewsHeadlines



}
