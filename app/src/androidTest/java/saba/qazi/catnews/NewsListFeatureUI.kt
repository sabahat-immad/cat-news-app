package saba.qazi.catnews

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import saba.qazi.catnews.newslist.di.idlingResource


class NewsListFeatureUI : BaseUITest(){

    @Test
    fun displayTitleText() {
        assertDisplayed("Sky Cat News")
    }

    @Test
    fun displayBannerImage(){
        assertDisplayed(R.id.cat_banner)
        onView(withId(R.id.cat_banner)).check(matches(withDrawable(R.mipmap.cat_banner)))
    }
    @Test
    fun displayListOfCatNews(){

        onView(allOf(withId(R.id.story_headline), isDescendantOfA(nthChildOf(withId(R.id.news_list),0))))
            .check(matches(withText("Story Headline")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.story_text), isDescendantOfA(nthChildOf(withId(R.id.news_list),0))))
            .check(matches(withText("Story teaser text")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.cat_image), isDescendantOfA(nthChildOf(withId(R.id.news_list),1))))
            .check(matches(withDrawable(R.mipmap.cat_img)))
            .check(matches(isDisplayed()))
}
    @Test
    fun displaysLoaderWhenFetchingTheNewsList(){
        /*we have explicitly unregistered idling resource because if we dont, soon after we did
        * the http call the thread sleeps and wont detect the UI loader */
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoaderWhenNewsAreFetched(){

        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysWeblinksForWebListItems(){

        onView(allOf(withId(R.id.story_URL), isDescendantOfA(nthChildOf(withId(R.id.news_list),3))))
                .check(matches(withText("weblink url")))
                .check(matches(isDisplayed()))

    }

    @Test
    fun navigateToStoryDetailScreen(){

        onView(allOf(withId(R.id.cat_image), isDescendantOfA(nthChildOf(withId(R.id.news_list),1))))
                .perform(click())

        assertDisplayed(R.id.story_detail_fragment)
    }


}