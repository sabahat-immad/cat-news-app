package saba.qazi.catnews

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.core.AllOf
import org.junit.Test

class StoryDetailFeatureUI : BaseUITest() {

    @Test
    fun displayViewsInScreen(){
        Espresso.onView(
            AllOf.allOf(
                ViewMatchers.withId(R.id.cat_image),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.news_list), 1))
            )
        )
            .perform(ViewActions.click())

        assertDisplayed(R.id.story_detail_fragment)

        assertDisplayed(R.id.story_banner_image)
        assertDisplayed(R.id.story_headline_text)
        assertDisplayed(R.id.story_detail_items_container)
    }
}