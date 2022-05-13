package com.atahar.data.mapper

import com.atahar.data.api.FlickrSearchResponse
import com.atahar.domain.entities.FlkrPhoto
import com.atahar.domain.entities.FlkrPhotos

class FlickrSearchResponseMapper  {

    fun toFlkrPhotos(response: FlickrSearchResponse): FlkrPhotos {
        val photoItemList = response.photos.photo.map {
            FlkrPhoto(
                it.id,
                it.owner,
                it.secret,
                it.server,
                it.farm,
                it.title,
                it.ispublic,
                it.isfriend,
                it.isfamily
            )
        }

        return FlkrPhotos(
            response.photos.page,
            response.photos.pages,
            response.photos.perpage,
            response.photos.total,
            photoItemList
        )
    }
}