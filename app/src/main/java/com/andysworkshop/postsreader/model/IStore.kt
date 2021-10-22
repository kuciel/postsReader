package com.andysworkshop.postsreader.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow

interface IStore {
    val postsData: SharedFlow<List<PostData>>
    fun refreshPostsData(scope: CoroutineScope): Boolean
    suspend fun getUserByUserId(scope: CoroutineScope, userId: String): UsersData
}