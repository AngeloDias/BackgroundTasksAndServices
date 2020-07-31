package com.example.android.devbyteviewer.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
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
        Espresso.onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        3,
                        click()))

//        onData(inPosition(3))
//                .inAdapterView(withId(R.id.recycler_view))
//                .perform(click())
    }

    private fun inPosition(value: Int): Matcher<Any> {
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
