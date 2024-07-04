package com.lizaworks.photosget.network

import com.lizaworks.photosget.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkService {
    @Headers("Authorization: Client-ID ${BuildConfig.ClientID}")
    @GET("/photos")
    suspend fun getPhotos(): List<Photo>
}



