package saba.qazi.catnews.newslist.data

import com.google.gson.annotations.SerializedName

data class CatNewsHeadlines(
    @SerializedName("data")
    val newsData : List<NewsRaw>,
    val title: String
)