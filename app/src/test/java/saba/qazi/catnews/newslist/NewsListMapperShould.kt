package saba.qazi.catnews.newslist

import junit.framework.Assert.assertEquals
import org.junit.Test
import saba.qazi.catnews.newslist.data.NewsRaw
import saba.qazi.catnews.utils.BaseUntTest

class NewsListMapperShould : BaseUntTest() {

    private val newsRaw = NewsRaw("1", "20-10-19","dfdf","ds",null,
    "fdf","story","ds","dfdf")

    private val newsRawAdvert = NewsRaw("1", "20-10-19","dfdf","ds",null,
        "fdf","advert","ds","dfdf")

    private val mapper = NewsListMapper()

    private val newsLists = mapper(listOf(newsRaw))

    private val news = newsLists[0]
    private val newsAdvert = mapper(listOf(newsRawAdvert))[0]

    @Test
    fun keepSameId() {
        assertEquals(newsRaw.id, news.id)
    }


    @Test
    fun keepSameCategory() {
        assertEquals(newsRaw.type, news.type)
    }

    @Test
    fun showNoImageWhenAdvert() {
        assertEquals(0, newsAdvert.image)
    }

}