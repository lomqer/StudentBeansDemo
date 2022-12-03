package com.dziaber.studentbeansdemo
import com.dziaber.studentbeansdemo.data.PhotoRepository
import org.junit.Assert.assertThrows
import org.junit.Test

class PhotoRepositoryTest {
    @Test
    fun repositoryInvalidUrl() {
        val repo = PhotoRepository("!abcd")
        assertThrows(IllegalArgumentException::class.java, repo::getPhotos)
    }
}