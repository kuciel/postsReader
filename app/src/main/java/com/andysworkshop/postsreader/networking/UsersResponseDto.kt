package com.andysworkshop.postsreader.networking

import com.google.gson.annotations.SerializedName

data class UsersResponseDto(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String
)
