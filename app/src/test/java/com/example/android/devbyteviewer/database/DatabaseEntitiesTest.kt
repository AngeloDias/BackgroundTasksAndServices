package com.example.android.devbyteviewer.database

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
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
        // defining the object to be mocked
        var videos = mock(ArrayList<DatabaseVideo>().javaClass)

        // defining the value to be expected for assert method
        `when`(videos.isEmpty()).thenReturn(false)
        Assert.assertFalse(videos.isEmpty())

        val databaseVideoA = DatabaseVideo(
                "https://www.youtube.com/watch?v=jKMNIB9sk18",
                "",
                "Warm-Up Exercises That Sound Pleasant But Make You WORK",
                "Warm-up exercises in ii-V-I that will make you WORK, but still sound pleasant to the ear.",
                "")

        `when`(videos.size).thenReturn(1)
        MatcherAssert.assertThat(videos.size, `is`(1))

        `when`(videos.contains(databaseVideoA)).thenReturn(true)
        Assert.assertTrue(videos.contains(databaseVideoA))

        `when`(videos[-1]).thenThrow(ArrayIndexOutOfBoundsException(-1))
        try {
            videos[-1]
            Assert.fail("The index must be greater than or equal to 0!")
        } catch (e: ArrayIndexOutOfBoundsException) {
            e.localizedMessage
        }

        verify(videos, atMost(1)).isEmpty()
        verify(videos, times(1)).isEmpty()
        verify(videos, never()).trimToSize()
        verify(videos, atMostOnce()).contains(databaseVideoA)
        verify(videos, atMostOnce()).size
        verify(videos, atLeastOnce())[ArgumentMatchers.anyInt()]
        verifyNoMoreInteractions(videos)

        videos = ArrayList()
        val spyVideos = spy(videos) // spy is used to wrap a real object to test

        // doReturn is used when expecting to have an Exception on method called
        doReturn(databaseVideoA).`when`(spyVideos)[0]

        val ret = spyVideos[0]

        Assert.assertEquals(databaseVideoA, ret)

        `when`(spyVideos.add(ArgumentMatchers.any())).thenReturn(false)
        Assert.assertFalse(spyVideos.add(ArgumentMatchers.any()))

        verify(spyVideos, atLeastOnce())[0]
        verify(spyVideos)[ArgumentMatchers.eq(0)]
        verify(spyVideos, times(1))[0]

        videos = mock(ArrayList<DatabaseVideo>().javaClass)

        videos.clear()
        verify(videos, only()).clear()
    }

}
