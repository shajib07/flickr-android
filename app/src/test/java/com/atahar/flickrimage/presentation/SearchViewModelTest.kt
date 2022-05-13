package com.atahar.flickrimage.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atahar.domain.usecases.GetPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    private val getPhotosUseCase = mock<GetPhotosUseCase>()
    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        searchViewModel = SearchViewModel(getPhotosUseCase)
        searchViewModel._status.value = LoadingStatus.LOADING
    }

    @Test
    fun `test initial loading status to loading`() = runBlocking {
        searchViewModel.status
        Assert.assertEquals(searchViewModel.status.value, LoadingStatus.LOADING)
    }

    @Test
    fun `test get photos return zero`() = runBlocking {
        searchViewModel.getPhotos("Berlin")
        Assert.assertNotEquals(searchViewModel.photos.value?.size, 0)
    }

}