package com.atahar.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoSearchApi {

    @GET("services/rest/")
    suspend fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("text") text: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("safe_search") safe_search: Int,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("format") format: String
    ): Response<FlickrSearchResponse>

}