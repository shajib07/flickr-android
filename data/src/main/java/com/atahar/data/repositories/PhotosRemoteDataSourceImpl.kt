package com.atahar.data.repositories

import com.atahar.data.api.PhotoSearchApi
import com.atahar.data.mapper.FlickrSearchResponseMapper
import com.atahar.domain.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

private const val API_KEY = "37ad288835e4c64fc0cb8af3f3a1a65d"
private const val METHOD = "flickr.photos.search"
private const val PER_PAGE = 30
private const val FORMAT = "json"


class PhotosRemoteDataSourceImpl (
    private val service: PhotoSearchApi,
    private val mapper: FlickrSearchResponseMapper
) : PhotosRemoteDataSource {

    override suspend fun getPhotos(searchText: String, currentPage: Int) =

        withContext(Dispatchers.IO) {
            try {
                val response = service.getPhotos(
                    METHOD,
                    API_KEY,
                    searchText,
                    1,
                    1,
                    currentPage,
                    PER_PAGE,
                    FORMAT
                )
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toFlkrPhotos(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (ex: Exception) {
                return@withContext Result.Error(ex)
            }
        }
}
