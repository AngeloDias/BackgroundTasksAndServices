package com.example.android.devbyteviewer.domain

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class ModelsTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Test
    fun videoTest() {
        val url = "https://www.youtube.com/watch?v=swdWg1fm1zY"
        val video = Video(
                "Se vos possuo nada mais me atrai - Dom Henrique Soares (23/11/17)",
                "\"Se vos possuo, nada mais me atrai\" é uma das #pregações mais belas de" +
                " Dom Henrique Soares ( in memoria ). #DomHenrique nos fala sobre São Paulo " +
                "sua fé e seu temor a Deus, Ele nos fala que o Espírito Santo está em nós " +
                "a partir do momento que somos batizados em nome de #Jesus.",
                "https://www.youtube.com/watch?v=swdWg1fm1zY",
                "",
                "")

        val spyVideo = spy(video)

        `when`(spyVideo.url).thenReturn(url)
        Assert.assertEquals(spyVideo.url, url)
    }

}
