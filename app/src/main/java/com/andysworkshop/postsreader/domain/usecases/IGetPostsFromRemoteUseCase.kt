package com.andysworkshop.postsreader.domain.usecases

import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult

interface IGetPostsFromRemoteUseCase {
    suspend fun invoke(): PostsDataRequestResult
}