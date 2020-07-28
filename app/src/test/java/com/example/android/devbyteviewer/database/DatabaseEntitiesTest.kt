package com.example.android.devbyteviewer.database

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class DatabaseEntitiesTest {

    /**
     * Solving "The @Rule 'mockitoRule' must be public" error:
     * https://proandroiddev.com/fix-kotlin-and-new-activitytestrule-the-rule-must-be-public-f0c5c583a865
     * */
    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Test
    fun videosTest() {
        val videos = mock(ArrayList<DatabaseVideo>().javaClass)

        `when`(videos.isEmpty()).thenReturn(true)
        Assert.assertTrue(videos.isEmpty())

        val databaseVideoA = DatabaseVideo(
                "https://www.youtube.com/watch?v=jKMNIB9sk18",
                "",
                "Warm-Up Exercises That Sound Pleasant But Make You WORK",
                "Warm-up exercises in ii-V-I that will make you WORK, but still sound pleasant to the ear.",
                "")

        videos.add(databaseVideoA)

        `when`(videos.size).thenReturn(1)
        MatcherAssert.assertThat(videos.size, `is`(1))

        `when`(videos.contains(databaseVideoA)).thenReturn(true)
        Assert.assertTrue(videos.contains(databaseVideoA))
    }

}
