package com.example.android.devbyteviewer.database

import android.content.Context
import androidx.lifecycle.LiveData
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class RoomTest {
    @Mock
    private lateinit var mockApplicationContext: Context
    private lateinit var videosDatabase: VideosDatabase

    @Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Test
    fun testGetVideos() {
        videosDatabase = getDatabase(mockApplicationContext)
        val videos = ArrayList<DatabaseVideo>()

        `when`(videosDatabase.videoDao.getVideos().value).thenReturn(videos)

        val databaseVideoA = DatabaseVideo(
                "https://www.youtube.com/watch?v=jKMNIB9sk18",
                "",
                "Warm-Up Exercises That Sound Pleasant But Make You WORK",
                "Warm-up exercises in ii-V-I that will make you WORK, but still sound pleasant to the ear.",
                "")

        videos.add(databaseVideoA)
        videosDatabase.videoDao.insertAll(databaseVideoA)

        `when`(videosDatabase.videoDao.getVideos().value).thenReturn(videos)
    }

    @Test
    fun testInsertAll() {
        val databaseVideoA = DatabaseVideo(
                "https://www.youtube.com/watch?v=W2vYRl24TNc",
                "",
                "Dom Henrique Soares fala sobre as crises na Igreja",
                "Dom Henrique Soares e Prof. Felipe Aquino falam sobre diversos assuntos relacionados à Igreja nos tempos atuais. Acompanhe!",
                "")
        val databaseVideoB = DatabaseVideo(
                "https://www.youtube.com/watch?v=jKMNIB9sk18",
                "",
                "Warm-Up Exercises That Sound Pleasant But Make You WORK",
                "Warm-up exercises in ii-V-I that will make you WORK, but still sound pleasant to the ear.",
                "")

        videosDatabase = getDatabase(mockApplicationContext)

        videosDatabase.videoDao.insertAll(databaseVideoA, databaseVideoB)
        `when`(videosDatabase.videoDao.getVideos().value!!.isNotEmpty()).thenReturn(false)
    }

}
