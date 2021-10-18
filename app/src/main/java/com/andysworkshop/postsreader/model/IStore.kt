package com.andysworkshop.postsreader.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface IStore {
    suspend fun refreshPostsData(scope: CoroutineScope): List<PostData>
    suspend fun getUserByUserId(scope: CoroutineScope, userId: String): UsersData
}