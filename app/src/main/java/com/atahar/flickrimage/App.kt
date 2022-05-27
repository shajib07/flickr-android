package com.atahar.flickrimage

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

/*
    private val photosRepo: PhotosRepoImpl
        get() = ServiceLocator.providePhotosRepo(this)

    val getPhotosUseCase: GetPhotosUseCase
        get() = GetPhotosUseCase(photosRepo)

*/
}