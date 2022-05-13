package com.atahar.domain.usecases

import com.atahar.domain.repositories.PhotosRepo

class GetPhotosUseCase (private val photosRepo: PhotosRepo) {

    suspend operator fun invoke(searchText: String, currentPage: Int) =
        photosRepo.getRemotePhotos(searchText, currentPage)
}