package saba.qazi.catnews.newslist

import saba.qazi.catnews.R
import saba.qazi.catnews.newslist.data.News
import saba.qazi.catnews.newslist.data.NewsRaw
import javax.inject.Inject

//function1 means newslistmapper class will have a function with 1 input and 1 output
class NewsListMapper @Inject constructor(): Function1<List<NewsRaw>, List<News>>{


    override fun invoke(newsListRaw: List<NewsRaw>): List<News> {
       return newsListRaw.map {
           val headline = getHeadline(it)
           val url = getUrl(it)
           val image = getImage(it)

           News(it.id,it.creationDate,headline,it.modifiedDate,it.teaserImage,it.teaserText,it.type,
               url,it.weblinkUrl, image)
       }
    }

    private fun getImage(it: NewsRaw): Int {
        return when (it.type) {
            "advert" -> 0
            else -> R.mipmap.cat_img
        }
    }

    private fun getUrl(it: NewsRaw): String {
        return when (it.type) {
            "advert" -> it.url
            "weblink" -> it.weblinkUrl
            else -> ""
        }
    }

    private fun getHeadline(it: NewsRaw): String {
        return when (it.type) {
            "advert" -> "Advertisement"
            else -> it.headline
        }
    }

}
