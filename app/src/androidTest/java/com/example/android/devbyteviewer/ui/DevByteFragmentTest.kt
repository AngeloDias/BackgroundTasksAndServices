package com.example.android.devbyteviewer.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.android.devbyteviewer.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DevByteFragmentTest {
    @get:Rule
    var activityTestRule = ActivityTestRule<DevByteActivity>(DevByteActivity::class.java)

    @Test
    fun scrollToItemToClickTest() {
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        3,
                        scrollTo()))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        9,
                        scrollTo()))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        12,
                        scrollTo()))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        17,
                        scrollTo()))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        21,
                        scrollTo()))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        27,
                        scrollTo()))
                .check(matches(isDisplayed()))
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        31,
                        scrollTo()))
                .check(matches(isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<DevByteViewHolder>(
                        9,
                        click()))

//        onData(inListPosition(3))
//                .inAdapterView(withId(R.id.list))
//                .perform(click())
    }

    private fun inListPosition(value: Int): Matcher<Any> {
        return object : BoundedMatcher<Any, DevByteViewHolder>(DevByteViewHolder::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("has value $value")
            }

            override fun matchesSafely(item: DevByteViewHolder?): Boolean {
                return item?.itemId == value.toLong()
            }

        }
    }

}
