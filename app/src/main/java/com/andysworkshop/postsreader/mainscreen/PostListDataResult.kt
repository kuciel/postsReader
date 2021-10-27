package com.andysworkshop.postsreader.mainscreen

sealed class PostListDataResult {
    data class Success(val value: List<PostListData>): PostListDataResult()
    data class Error(val message: String): PostListDataResult()
}
