package com.lizaworks.photosget.network

import kotlinx.serialization.Serializable

@Serializable
data class Photo(val id: String, val likes: Int, val description: String?, val urls: Urls)

@Serializable
data class Urls(val raw :String, val full:String, val regular:String,val small: String,val thumb: String)