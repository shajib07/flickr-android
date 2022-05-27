package com.atahar.flickrimage.di

import com.atahar.data.api.PhotoSearchApi
import com.atahar.data.repositories.PhotosRemoteDataSource
import com.atahar.data.repositories.PhotosRemoteDataSourceImpl
import com.atahar.data.repositories.PhotosRepoImpl
import com.atahar.domain.repositories.PhotosRepo
import com.atahar.domain.usecases.GetPhotosUseCase
import com.atahar.entities.mapper.FlickrSearchResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Singleton
    @Provides
    fun providePhotosRemoteDataSource(
        service: PhotoSearchApi
    ): PhotosRemoteDataSource {
        return PhotosRemoteDataSourceImpl(service, FlickrSearchResponseMapper())
    }

    @Singleton
    @Provides
    fun providePhotosRepo(
        photosRemoteDataSource: PhotosRemoteDataSource
    ): PhotosRepo {
        return PhotosRepoImpl(photosRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGetPhotosUseCase(photosRepo: PhotosRepo): GetPhotosUseCase {
        return GetPhotosUseCase(photosRepo)
    }
}