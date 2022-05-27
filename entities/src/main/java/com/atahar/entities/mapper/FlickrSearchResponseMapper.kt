package com.atahar.entities.mapper

import com.atahar.entities.FlkrPhoto
import com.atahar.entities.FlkrPhotos
import com.atahar.entities.api.FlickrSearchResponse

class FlickrSearchResponseMapper {

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