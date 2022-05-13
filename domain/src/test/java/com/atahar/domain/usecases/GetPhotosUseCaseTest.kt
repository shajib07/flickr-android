package com.atahar.domain.usecases

import com.atahar.domain.common.Result
import com.atahar.domain.data.FakePhotoRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPhotosUseCaseTest {

    private lateinit var getPhotosUseCase: GetPhotosUseCase

    @Before
    fun setUp() {
        getPhotosUseCase = GetPhotosUseCase(FakePhotoRepo())
    }

    @Test
    fun `get photos from remote with success`() {
        runBlocking {
            val result = getPhotosUseCase.invoke("Berlin", 1)
            val isSuccess = result is Result.Success
            Assert.assertTrue(isSuccess)
        }
    }

    @Test
    fun `get photos from remote`() {
        runBlocking {
            when (val result = getPhotosUseCase.invoke("Berlin", 1)) {
                is Result.Success -> {
                    Assert.assertTrue(result.data.photo.isNotEmpty())
                }
                is Result.Error -> {
                    Assert.assertNotNull(result.exception.message)
                }
            }
        }
    }

}