package com.example.android.devbyteviewer.viewmodels

import android.app.Application
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.repository.VideosRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DevByteViewModelTest {

    @MockK
    lateinit var videosRepository: VideosRepository

    @MockK
    lateinit var devByteViewModel: DevByteViewModel

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun getVideosFromVideosRepository() {

        coEvery { videosRepository.refreshVideos() }

        Assert.assertNotNull(devByteViewModel.playlist.value)

//        videosRepository.videos

    }
}