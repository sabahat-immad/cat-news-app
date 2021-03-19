package saba.qazi.catnews

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
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


@RunWith(AndroidJUnit4::class)
class NewsListUIFeature {

    val activityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get


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
        Thread.sleep(4000)

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
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoaderWhenNewsAreFetched(){
        Thread.sleep(4000)
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysWeblinksForWebListItems(){
        Thread.sleep(4000)
        onView(allOf(withId(R.id.story_URL), isDescendantOfA(nthChildOf(withId(R.id.news_list),3))))
                .check(matches(withText("weblink url")))
                .check(matches(isDisplayed()))

    }

    @Test
    fun navigateToStoryDetailScreen(){
        Thread.sleep(4000)

        onView(allOf(withId(R.id.cat_image), isDescendantOfA(nthChildOf(withId(R.id.news_list),1))))
                .perform(click())

        assertDisplayed(R.id.story_detail_fragment)
    }
    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }

}