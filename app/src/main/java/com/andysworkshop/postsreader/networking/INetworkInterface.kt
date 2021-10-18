package com.andysworkshop.postsreader.networking

import com.andysworkshop.postsreader.model.PostData
import com.andysworkshop.postsreader.model.UsersData

interface INetworkInterface {
    suspend fun requestPostsData(): List<PostData>
    suspend fun requestUserData(id: String): UsersData
}