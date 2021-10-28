package com.andysworkshop.postsreader.networking.usecases

import com.andysworkshop.postsreader.domain.data.PostsDataRequestResult
import com.andysworkshop.postsreader.domain.usecases.IGetPostsFromRemoteUseCase
import com.andysworkshop.postsreader.networking.INetworkInterface
import javax.inject.Inject

class GetPostsFromRemoteUseCase @Inject constructor(val networkInterface: INetworkInterface):
    IGetPostsFromRemoteUseCase {
    override suspend fun invoke(): PostsDataRequestResult {
        return networkInterface.requestPostsData()
    }
}