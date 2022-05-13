package com.atahar.data.repositories

import com.atahar.domain.common.Result
import com.atahar.domain.entities.FlkrPhotos
import com.atahar.domain.repositories.PhotosRepo


class PhotosRepoImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource
) : PhotosRepo {

    override suspend fun getRemotePhotos(searchText: String, currentPage: Int)
            : Result<FlkrPhotos> {
        return photosRemoteDataSource.getPhotos(searchText, currentPage)
    }
}