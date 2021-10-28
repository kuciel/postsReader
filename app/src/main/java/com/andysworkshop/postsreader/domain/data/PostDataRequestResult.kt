package com.andysworkshop.postsreader.domain.data

sealed class PostsDataRequestResult {
    data class Success(val value: List<PostData>): PostsDataRequestResult()
    data class Error(val message: String): PostsDataRequestResult()
}