package saba.qazi.catnews.newslist.data

data class News(
    val id: String?,
    val creationDate: String?,
    val headline: String?,
    val modifiedDate: String?,
    val teaserImage: TeaserImage?,
    val teaserText: String?,
    val type: String?,
    val url: String?,
    val weblinkUrl: String?,
    val image : Int
)