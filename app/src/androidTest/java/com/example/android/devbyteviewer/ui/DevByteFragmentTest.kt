package com.example.android.devbyteviewer.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.android.devbyteviewer.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DevByteFragmentTest {
    @get:Rule
    var activityTestRule = ActivityTestRule<DevByteActivity>(DevByteActivity::class.java)

    @Test
    fun scrollToItemToClickTest() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        3,
                        click()))
    }

}
