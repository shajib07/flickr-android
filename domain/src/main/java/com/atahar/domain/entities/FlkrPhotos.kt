package com.atahar.domain.entities

data class FlkrPhotos (
    val page: Int,
    val pages: Int,
    val perpage: String,
    val total: String,
    val photo: List<FlkrPhoto>
)