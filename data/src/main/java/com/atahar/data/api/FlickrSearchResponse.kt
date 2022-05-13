package com.atahar.data.api

import com.squareup.moshi.Json

data class FlickrSearchResponse(
    @Json(name = "photos") val photos: FlickrPhotos
)

data class FlickrPhotos(
    @Json(name = "page") val page: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "perpage") val perpage: String,
    @Json(name = "total") val total: String,
    @Json(name = "photo") val photo: List<FlickrPhoto>
)

data class FlickrPhoto(
    @Json(name = "id") val id: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "secret") val secret: String,
    @Json(name = "server") val server: String,
    @Json(name = "farm") val farm: String,
    @Json(name = "title") val title: String,
    @Json(name = "ispublic") val ispublic: String,
    @Json(name = "isfriend") val isfriend: String,
    @Json(name = "isfamily") val isfamily: String
)

