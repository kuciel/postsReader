package com.andysworkshop.postsreader.networking

import com.andysworkshop.postsreader.domain.data.*

interface INetworkInterface {
    suspend fun requestPostsData(): PostsDataRequestResult
    suspend fun requestUserData(id: String): UserDataRequestResult
}