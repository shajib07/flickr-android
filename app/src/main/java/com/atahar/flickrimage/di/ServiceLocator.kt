package com.atahar.flickrimage.di

import android.content.Context
import com.atahar.data.api.RetrofitClient
import com.atahar.data.mapper.FlickrSearchResponseMapper
import com.atahar.data.repositories.PhotosRemoteDataSourceImpl
import com.atahar.data.repositories.PhotosRepoImpl


object ServiceLocator {

/*    @Volatile
    var photosRepo: PhotosRepoImpl? = null

    fun providePhotosRepo(context: Context): PhotosRepoImpl {
        synchronized(this) {
            return photosRepo ?: createPhotosRepo(context)
        }
    }

    private fun createPhotosRepo(context: Context): PhotosRepoImpl {
        val newRepo =
            PhotosRepoImpl(
                PhotosRemoteDataSourceImpl(
                    RetrofitClient.photoSearchApi,
                    FlickrSearchResponseMapper()
                )
            )

        photosRepo = newRepo
        return newRepo
    }*/
}