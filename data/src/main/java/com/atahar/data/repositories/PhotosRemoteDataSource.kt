package com.atahar.data.repositories

import com.atahar.domain.common.Result
import com.atahar.domain.entities.FlkrPhotos

interface PhotosRemoteDataSource {
    suspend fun getPhotos(searchText: String, currentPage: Int): Result<FlkrPhotos>
}