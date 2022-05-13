package com.atahar.domain.entities

data class FlkrPhoto(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: String,
    val title: String,
    val ispublic: String,
    val isfriend: String,
    val isfamily: String
)