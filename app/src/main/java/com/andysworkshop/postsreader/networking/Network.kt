package com.andysworkshop.postsreader.networking

import com.andysworkshop.postsreader.domain.data.*

import javax.inject.Inject

class Network @Inject constructor(
    private val apiInterface: IRetrofitApiInterface
) : INetworkInterface {

    override suspend fun requestPostsData(): PostsDataRequestResult {
        return try {
            PostsDataRequestResult.Success(apiInterface.getPosts().map {
                PostData(
                    title = it.title,
                    body = it.body,
                    id = it.id,
                    userId = it.userId
                )
            })
        } catch (error: Throwable) {
            return PostsDataRequestResult.Error(error.message ?: "unknown error")
        }
    }

    override suspend fun requestUserData(id: String): UserDataRequestResult {
        return try {
            UserDataRequestResult.Success(apiInterface.getUsersById(id))
        } catch (error: Throwable) {
            return UserDataRequestResult.Error(error.message ?: "unknown error")
        }
    }
}