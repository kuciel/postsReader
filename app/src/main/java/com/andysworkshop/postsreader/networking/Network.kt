package com.andysworkshop.postsreader.networking

import com.andysworkshop.postsreader.model.PostData
import com.andysworkshop.postsreader.model.UsersData

import javax.inject.Inject

class Network @Inject constructor(
    private val apiInterface: IRetrofitApiInterface
) : INetworkInterface {

    override suspend fun requestPostsData(): List<PostData> {
        return apiInterface.getPosts().map {
            PostData(
                title = it.title,
                body = it.body,
                id = it.id,
                userId = it.userId
            )
        }
    }

    override suspend fun requestUserData(id: String): UsersData {
        return apiInterface.getUsersById(id)
    }
}