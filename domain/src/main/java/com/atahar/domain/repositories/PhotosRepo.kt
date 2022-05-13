package com.atahar.domain.repositories

import com.atahar.domain.common.Result
import com.atahar.domain.entities.FlkrPhotos

interface PhotosRepo {
    suspend fun getRemotePhotos(searchText: String, currentPage: Int): Result<FlkrPhotos>
}