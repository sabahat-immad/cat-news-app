package saba.qazi.catnews.newslist.data

data class NewsRaw(
    val id: String,
    val creationDate: String,
    val headline: String,
    val modifiedDate: String,
    val teaserImage: TeaserImage?,
    val teaserText: String,
    val type: String,
    val url: String,
    val weblinkUrl: String
)
