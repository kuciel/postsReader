package com.andysworkshop.postsreader.networking

import com.google.gson.annotations.SerializedName

data class PostsResponseDto (
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("userId")
    var userId: String
)