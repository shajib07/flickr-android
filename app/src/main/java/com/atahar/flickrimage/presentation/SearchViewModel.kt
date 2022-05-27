package com.atahar.flickrimage.presentation

import androidx.lifecycle.*
import com.atahar.domain.usecases.GetPhotosUseCase
import com.atahar.domain.common.Result
import com.atahar.entities.FlkrPhoto
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class LoadingStatus { LOADING, ERROR, DONE, NONE }

class SearchViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    val _status = MutableLiveData<LoadingStatus>()
    val status: LiveData<LoadingStatus> = _status

    private var _currentPage = 0
    val currentPage: Int
        get() = _currentPage

    private var _totalPage = Int.MAX_VALUE;
    val totalPage: Int
        get() = _totalPage

    private val _photos = MutableLiveData<ArrayList<FlkrPhoto>>()
    val photos: LiveData<ArrayList<FlkrPhoto>>
        get() = _photos

    fun resetData() {
        _currentPage = 0
        _totalPage = Int.MAX_VALUE
        _photos.value = arrayListOf()
    }

    fun getPhotos(search: String) {
        _currentPage++
        viewModelScope.launch {
            _status.value = LoadingStatus.LOADING
            when (val result = getPhotosUseCase.invoke(search, _currentPage)) {
                is Result.Success -> {
                    if (currentPage == 1 && result.data.photo.isEmpty()) {
                        _status.value = LoadingStatus.NONE
                    }
                    else {
                        _photos.value!!.addAll(result.data.photo)
                        _photos.value = _photos.value
                        _totalPage = result.data.pages
                        _status.value = LoadingStatus.DONE
                    }
                }
                is Result.Error -> {
                    _status.value = LoadingStatus.ERROR
                    _photos.value = arrayListOf()
                }
            }
        }
    }

    class SearchViewModelFactory(
        private val getPhotosUseCase: GetPhotosUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel(
                getPhotosUseCase
            ) as T
        }
    }

}
