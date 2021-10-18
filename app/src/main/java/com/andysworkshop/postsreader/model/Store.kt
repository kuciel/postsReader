package com.andysworkshop.postsreader.model

import com.andysworkshop.postsreader.networking.INetworkInterface
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class Store @Inject constructor(
    private val networkInterface: INetworkInterface
) : IStore {

    override suspend fun refreshPostsData(scope: CoroutineScope): List<PostData> {
        return networkInterface.requestPostsData()
    }

    override suspend fun getUserByUserId(scope: CoroutineScope, userId: String): UsersData {
        return networkInterface.requestUserData(userId)
    }
}