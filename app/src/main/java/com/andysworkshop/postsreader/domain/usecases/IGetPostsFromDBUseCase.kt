package com.andysworkshop.postsreader.domain.usecases

import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult

interface IGetPostsFromDBUseCase {
    fun invoke(): PostsDataRequestResult
}