package com.example.juli.network

import com.squareup.moshi.Json

data class Foto(
    val id : String, @Json(name = "img_src") val imgSrcUrl: String, val fecha : String, val mensaje : String
)