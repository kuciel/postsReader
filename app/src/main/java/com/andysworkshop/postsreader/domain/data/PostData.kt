package com.andysworkshop.postsreader.domain.data

data class PostData(
        var title: String,
        var body: String,
        var id: String,
        var userId: String,
        var userName: String = ""
    )
