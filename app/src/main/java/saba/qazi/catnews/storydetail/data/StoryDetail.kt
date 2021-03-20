package saba.qazi.catnews.storydetail.data

data class StoryDetail(
    val contents: List<Content>,
    val creationDate: String,
    val headline: String,
    val heroImage: HeroImage,
    val id: String,
    val modifiedDate: String
)