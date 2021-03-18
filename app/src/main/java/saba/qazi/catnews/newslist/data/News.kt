package saba.qazi.catnews.newslist.data

data class News(
    val creationDate: String,
    val headline: String,
    val id: String,
    val modifiedDate: String,
    val teaserImage: TeaserImage,
    val teaserText: String,
    val type: String,
    val url: String,
    val weblinkUrl: String
)