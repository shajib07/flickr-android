package com.atahar.domain.data

import com.atahar.domain.common.Result
import com.atahar.domain.entities.FlkrPhoto
import com.atahar.domain.entities.FlkrPhotos
import com.atahar.domain.repositories.PhotosRepo

class FakePhotoRepo : PhotosRepo {
    override suspend fun getRemotePhotos(searchText: String, currentPage: Int): Result<FlkrPhotos> {
        return getFakePhotos()
    }

    private fun getFakePhotos(): Result<FlkrPhotos> {
        val photo = FlkrPhoto(
            "id",
            "owner",
            "secret",
            "server",
            "farm",
            "title",
            "ispublic",
            "isfriend",
            "isfamily"
        )
        val photos = arrayListOf(photo)
        val flkrPhotos = FlkrPhotos(
            1,
            20,
            "5",
            "100",
            photos
        )
        return Result.Success(flkrPhotos)
    }
}